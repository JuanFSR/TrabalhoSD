import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { SocketClientService } from '@app/service/socket-client.service';

@Component({
  selector: 'app-page-login',
  templateUrl: './page-login.component.html',
  styleUrls: ['./page-login.component.scss']
})
export class PageLoginComponent implements OnInit {
  formLogin: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private serviceSocket: SocketClientService,

  ) { 
    this.formLogin = this.formBuilder.group({
      email: [''],
      username: [''],
    });
  }

  
  ngOnInit(): void {
  }

  onSubmit() {
    console.log(this.formLogin.controls.email)
    // console.log(this.formLogin.controls.username)
  }

}
