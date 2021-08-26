import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SocketClientService } from '@app/service/socket-client.service';

@Component({
  selector: 'app-subscribe-topic',
  templateUrl: './subscribe-topic.component.html',
  styleUrls: ['./subscribe-topic.component.scss']
})
export class SubscribeTopicComponent implements OnInit {

  form: FormGroup;
  submitted = false;

  @Output() 
  topicoEvent = new EventEmitter();

  constructor(private formBuilder: FormBuilder, 
    private socketService: SocketClientService) { 
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      topico: ['', Validators.required],
    });
  }

  get f() { return this.form!.controls; }

  onSubmit() {

    const topico = this.f.topico.value;

    this.socketService.createSubscription(topico);

    this.topicoEvent.emit(topico);
  }

}
