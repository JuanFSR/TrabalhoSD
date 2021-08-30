import { Component, Input, OnInit } from '@angular/core';
import { SocketClientService } from '@app/service/socket-client.service';

@Component({
  selector: 'app-page-salas',
  templateUrl: './page-salas.component.html',
  styleUrls: ['./page-salas.component.scss']
})
export class PageSalasComponent implements OnInit {
  topics: Array<string> = Array();

  constructor(private socketService: SocketClientService) { 
    socketService.connectSocket()
  }

  ngOnInit(): void {
  }

  addTopico(topico: string): void {
    this.topics.push(topico);
  }

}
