import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-broker-msg',
  templateUrl: './broker-msg.component.html',
  styleUrls: ['./broker-msg.component.scss']
})
export class BrokerMsgComponent implements OnInit {

  @Input()
  mensagem: string = '';

  constructor() { 

  }

  ngOnInit(): void {
  }

}
