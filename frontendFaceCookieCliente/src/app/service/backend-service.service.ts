import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BackendServiceService {

  corsHeaders: any;

  constructor(
    private http: HttpClient,
  ) { }


  criaPlayer(name: string, email: string) {
    let json={
      name: '',
      email: ''
    }
    json.name = name;
    json.email = email;

    return this.http.post<any>('http://127.168.0.1:8080/player/create', json).pipe(
      map(( data => {
        return data;
      }))
    )
  }

  getSala() {
    return this.http.get<any>('http://127.168.0.1:8080/game-room/').pipe(
      map(( data => {
        return data;
      }))
    )
  }

  joinSala(id: number, email: string) {
    let json = {
      email: ''
    }

    json.email = email;

    return this.http.post<any>('http://127.168.0.1:8080/game-room/join/' + id, json).pipe(
      map(( data => {
        return data;
      }))
    )
  }

  exitSala(id: number, email: string) {
    let json = {
      email: ''
    }

    json.email = email;

    return this.http.post<any>('http://127.168.0.1:8080/game-room/exit/' + id, json).pipe(
      map(( data => {
        return data;
      }))
    )
  }

  playSala(id: number, email: string, move: number) {
    let json = {
      email: '',
      move: -1
    }

    json.email = email;
    json.move = move;

    return this.http.post<any>('http://127.168.0.1:8080/game-room/play/' + id, json).pipe(
      map(( data => {
        return data;
      }))
    )
  }

  initSala(id: number) {
    return this.http.get<any>('http://127.168.0.1:8080/game-room/init/' + id).pipe(
      map(( data => {
        return data;
      }))
    )
  }

  resultSala(id: number) {
    return this.http.get<any>('http://127.168.0.1:8080/game-room/result/' + id).pipe(
      map(( data => {
        return data;
      }))
    )
  }

  createSala(nome: string, email: string) {
    let json = {
      nome: '',
      email: ''
    }

    json.nome = nome;
    json.email = email;

    return this.http.post<any>('http://127.168.0.1:8080/game-room/create/', json).pipe(
      map(( data => {
        return data;
      }))
    )
  }

}
