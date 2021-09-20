import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  email: string = '';
  id: number = -1;

  constructor() { }

  setEmail(email: string){
    this.email = email;
  }

  getEmail(){
    return this.email;
  }

  setId(id: number){
    this.id = id;
  }

  getId(){
    return this.id;
  }
}
