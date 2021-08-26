import { Component, Input, OnInit } from '@angular/core';
import { EventSocket, EventTypes } from '@app/model/event.model';
import { SocketClientService } from '@app/service/socket-client.service';

@Component({
  selector: 'app-broker-topic',
  templateUrl: './broker-topic.component.html',
  styleUrls: ['./broker-topic.component.scss']
})
export class BrokerTopicComponent implements OnInit {

  @Input()
  topic: string = '';
  
  mensagens: Array<string> = Array();

  constructor(private socketService: SocketClientService) { 
    

  }

  ngOnInit(): void {
    
    this.socketService.byTopic(this.topic)
    .subscribe((event: EventSocket<any>) => {
      this.mensagens.push(JSON.stringify(event.payload, null, 2))
    });

    this.socketService.onEvent(EventTypes.Enum.ASSISTIDO_QUANTIDADE_MEDICO_ONLINE)
    .subscribe((payload: Object) => {
      // this.mensagens.push(JSON.stringify(payload, null, 2))
    });
    /*
    [...Array(1000).keys()].forEach((index) => {
      this.mensagens.push('Linha ' + index)
    });
    */
  }

}
