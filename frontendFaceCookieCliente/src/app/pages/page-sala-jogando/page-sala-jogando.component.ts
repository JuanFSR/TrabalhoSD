import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EventSocket, EventTypes } from '@app/model/event.model';
import { AuthService } from '@app/service/auth.service';
import { BackendServiceService } from '@app/service/backend-service.service';
import { SocketClientService } from '@app/service/socket-client.service';
import Cookies from 'js-cookie';

@Component({
  selector: 'app-page-sala-jogando',
  templateUrl: './page-sala-jogando.component.html',
  styleUrls: ['./page-sala-jogando.component.scss']
})
export class PageSalaJogandoComponent implements OnInit {
  jogada: number = -1;
  statusBotao: boolean = false;
  email: string = '';
  id: number = 0;
  qntdPlayers: number = 1;
  qntdMax: number = 4;
  initGame: boolean = false;
  qntdJogadas: number = 0;

  constructor(
    private router: Router,
    private socketService: SocketClientService,
    private backendService: BackendServiceService,
    private authService: AuthService,
  ) { }

  ngOnInit(): void {
    this.email = this.authService.getEmail();
    this.id = this.authService.getId();

    this.socketService.createSubscription("game-room-" + this.id);
    this.socketService.createSubscription(this.email);

    this.socketService
        .onEvent(EventTypes.Enum.JOGADOR_ENTROU_SALA)
        .subscribe((event: EventSocket<any>) => {
          this.qntdPlayers = ((event as unknown) as number);
          
          console.log(this.qntdPlayers, this.qntdMax);
          if(this.qntdPlayers == this.qntdMax){
            this.iniciarjogo();
          }
        }) 

    this.socketService
        .onEvent(EventTypes.Enum.JOGADOR_SAIU_SALA)
        .subscribe((event: EventSocket<any>) => {

          if(this.initGame){
            this.sairSala();
          }

          this.qntdPlayers = (event.payload as number);
        }) 

    this.socketService
        .onEvent(EventTypes.Enum.JOGADOR_JOGOU)
        .subscribe((event: EventSocket<any>) => {
          this.qntdJogadas += 1;
        }) 

    this.socketService
        .onEvent(EventTypes.Enum.JOGO_INICIOU)
        .subscribe((event: EventSocket<any>) => {
          this.initGame = true;
        }) 

  }

  verificarGanhador(){
    if(this.qntdJogadas == this.qntdPlayers) {
      this.backendService.resultSala(this.id).subscribe(() => {});
    }
  }

  fogo() {
    this.jogada = 4;
  }

  tesoura() {
    this.jogada = 2;
  }

  papel() {
    this.jogada = 1;
  }

  pedra() {
    this.jogada = 0;
  }

  agua() {
    this.jogada = 3;
  }

  iniciarjogo(){
    this.backendService.initSala(this.id).subscribe(() => {});
  }

  sairSala() {
    this.backendService.exitSala(this.id, this.email).subscribe(
      (data) => {
        this.socketService.cancelSubscription("game-room-" + this.id)
        this.socketService.cancelSubscription(this.email)
        this.router.navigate(['/salas']);
      },
      (err) => {
        console.log('erro: ', err)
      }
    )
    // Tenho que me desconectar do socket
  }

  enviaJogada() {
    // Depois de jogar eu desabilito o botão
    if(this.jogada != -1 && this.initGame){
      this.backendService.playSala(this.id, this.email, this.jogada).subscribe(
        (data) => {
          console.log('jogada enviada');
        },
        (error) => {
          console.log('error: ', error)
        }
      )
    } else {
      console.log('jogo nao iniciou')
    }
  }
}
