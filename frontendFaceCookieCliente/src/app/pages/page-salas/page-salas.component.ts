import { Component, Input, OnInit } from '@angular/core';
import { BackendServiceService } from '@app/service/backend-service.service';
import { SocketClientService } from '@app/service/socket-client.service';
import Cookies from 'js-cookie';

@Component({
  selector: 'app-page-salas',
  templateUrl: './page-salas.component.html',
  styleUrls: ['./page-salas.component.scss']
})
export class PageSalasComponent implements OnInit {
  topics: Array<Object> = Object();

  constructor(
    private socketService: SocketClientService,
    private serviceBackend: BackendServiceService,
    ) { 
    socketService.connectSocket()
  }
  
  ngOnInit(): void {
    let email = Cookies.get('emailLogin');
    console.log("Email",email)
  }
  
  addTopico(topico: string): void {
    this.topics.push(topico);
  }
  
  entraSala() { 
  }

  getSala() {
    this.serviceBackend.getSala().subscribe(
      (data) => {
        console.log(data);
        this.topics.push(data);
      },
      (error) => {
        console.log("Erro", error);
      }
    )
  }

}
