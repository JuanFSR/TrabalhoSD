import { Component, OnInit } from '@angular/core';
import { SocketClientService } from '@app/service/socket-client.service';

@Component({
  selector: 'app-page-main',
  templateUrl: './page-main.component.html',
  styleUrls: ['./page-main.component.scss']
})
export class PageMainComponent implements OnInit {

  topics: Array<string> = Array();

  constructor(private socketService: SocketClientService) { 
    socketService.connectSocket()
    /*
    [...Array(10).keys()].forEach((index) => {
      this.topics.push('Topico ' + index)
    });
    */
  }

  ngOnInit(): void {
  }

  addTopico(topico: string): void {
    this.topics.push(topico);
  }

}
