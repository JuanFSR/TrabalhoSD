import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SocketClientService } from '@app/service/socket-client.service';

@Component({
  selector: 'app-page-sala-jogando',
  templateUrl: './page-sala-jogando.component.html',
  styleUrls: ['./page-sala-jogando.component.scss']
})
export class PageSalaJogandoComponent implements OnInit {
  jogada: number = -1;
  statusBotao: boolean = false;
  constructor(
    private router: Router,
    private socketService: SocketClientService,
  ) { }

  ngOnInit(): void {
  }

  fogo() {
    console.log("Fogoo")
  }

  tesoura() {
    console.log("Tesoura")
  }

  papel() {
    console.log("Papel")
  }

  pedra() {
    console.log("Pedra")
  }

  agua() {
    console.log("Agua")
  }

  sairSala() {
    // Tenho que me desconectar do socket
    this.router.navigate(['/main']);
  }

  enviaJogada() {
    // Depois de jogar eu desabilito o botão
  }
}
