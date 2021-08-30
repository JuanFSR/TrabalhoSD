import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { Observable, Subject, Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { EventSocket, EventTypes } from '../model/event.model';

let tm;
@Injectable({
  providedIn: 'root'
})
export class SocketClientService {

  //Global Scope Observable (Todos os eventos recebidos passão por aqui);
  private observable = new Subject<EventSocket<any>>();

  private stompClient: any;

  private subscriptions = new Map<String, Subscription>();

  constructor( ) {

  }

  connectSocket() {

    const token = '';
    // const token = Cookies.get(AuthService.ATTR_TOKEN);
    
    if(this.stompClient) {
      return;
    }

    const ws = new SockJS(`${environment.adminApiUrl}/socket`);
    
    this.stompClient = Stomp.over(ws);
    this.stompClient.connect({
    }, async () => {

      this.subscriptions.forEach((value, key) => { 
        this.createSubscription((key as string));
      });

    }, () => {
      this.disconnect();

      tm = setTimeout(() => {
        this.connectSocket();
      }, 4000);
      
    })
  }

  public createSubscription(topic: string) {
    topic = `/${topic}`;

    let observableForThisSubscription = new Subject<any>();

    //Se o topico não está ativo ele é ativado.
    if(!this.subscriptions.has(topic)) {
      let subscription = this.stompClient.subscribe(topic, event => {
        let socketEvent: EventSocket<any> = JSON.parse(event.body);
        this.observable.next(socketEvent);
        observableForThisSubscription.next(socketEvent);
      });
      this.subscriptions.set(topic, subscription);
    }

    return observableForThisSubscription;
  }

  public cancelSubscription(topic: string) {
    topic = `/${topic}`;
    if(this.subscriptions.has(topic)) {
      this.subscriptions.get(topic).unsubscribe();
      this.subscriptions.delete(topic);
    }
  }

  public disconnect() {

    //Remove todos os topicos ativos
    [...this.subscriptions.values()].forEach((sub: Subscription) => {
      sub.unsubscribe();
    });

    this.subscriptions = new Map();

    if(this.stompClient?.connected) {
      this.stompClient.disconnect();
    }

    this.stompClient = undefined;
  }

  anyEvent = <T>() : Observable<Object> => {
    return this.observable.pipe(
      map((event: EventSocket<T>) =>  {
        console.log(event);
        return event;
      })
    );
  }

  byTopic = <T>(topico: string) : Observable<Object> => {
    return this.observable.pipe(
      filter((event: EventSocket<T>) => event.topico === topico),
      map((event: EventSocket<T>) =>  event)
    );
  }

  onEvent = <T>(tipo: EventTypes.Enum) : Observable<Object> => {
    return this.observable.pipe(
      filter((event: EventSocket<T>) => (event.tipo.toString()) === (EventTypes.Enum[tipo] as string)),
      map((event: EventSocket<T>) =>  {
        return event.payload;
      })
    );
  }
}
