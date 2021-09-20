import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { EventSocket, EventTypes } from '@app/model/event.model';
import { AuthService } from '@app/service/auth.service';
import { BackendServiceService } from '@app/service/backend-service.service';
import { SocketClientService } from '@app/service/socket-client.service';

@Component({
  selector: 'app-page-salas',
  templateUrl: './page-salas.component.html',
  styleUrls: ['./page-salas.component.scss']
})
export class PageSalasComponent implements OnInit {
  topics: Array<any>;
  email: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private socketService: SocketClientService,
    private serviceBackend: BackendServiceService,
    private router: Router,
    private authService: AuthService
    ) { 

    // crio a conexão
    socketService.connectSocket();
  }
  
  ngOnInit(): void {
    setTimeout(() => {
      // escuto um canal
      this.socketService.createSubscription("canal-geral");
  
      // escuto apenas o tipo SALA_CRIADA
      this.socketService
        .onEvent(EventTypes.Enum.SALA_CRIADA)
        .subscribe((event: EventSocket<any>) => {
          this.getSala();
        }) 
    }, 5 * 1000);

    this.email = this.authService.getEmail();
    
    this.getSala();
  }
  
  addTopico(topico: string): void {
    this.topics.push(topico);
  }
  
  entraSala(id: number) { 
    this.authService.setId(id)

    this.serviceBackend.joinSala(id, this.email).subscribe(
      (data) => {
        this.socketService.cancelSubscription("canal-geral");
        this.router.navigate(['/jogos']);
      },
      (err) => {
        console.log('error ', err);
      }
    )
  } 

  getSala() {
    this.serviceBackend.getSala().subscribe(
      (data) => {
        
        this.topics = data.list;
      },
      (error) => {
        console.log("Erro", error);
      }
    )
  }

}
