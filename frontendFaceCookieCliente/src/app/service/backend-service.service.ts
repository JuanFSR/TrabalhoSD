import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BackendServiceService {

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
    return this.http.post<any>('127.168.0.1:8080/player/create', json).pipe(
      map(( data => {
        return data;
      }))
    )
  }
}
