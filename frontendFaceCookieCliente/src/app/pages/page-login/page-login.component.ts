import { Component, Injectable, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SocketClientService } from '@app/service/socket-client.service';
import { AuthGuardService } from '@app/guards/auth-guard.service';
import { BackendServiceService } from '@app/service/backend-service.service';

@Component({
  selector: 'app-page-login',
  templateUrl: './page-login.component.html',
  styleUrls: ['./page-login.component.scss']
})

@Injectable()
export class PageLoginComponent implements OnInit {
  formLogin: FormGroup;

  // emailValido: boolean = true;

  constructor(
    private formBuilder: FormBuilder,
    private serviceBackend: BackendServiceService,
    private router: Router,
    private auth: AuthGuardService,

  ) { 
    this.formLogin = this.formBuilder.group({
      email: ['', [Validators.email, Validators.required]],
      username: ['', [Validators.required]],
    });
  }

  
  ngOnInit(): void {
  }

  onSubmit() {
    if(this.formLogin.controls.email.errors || this.formLogin.controls.username.errors ) {
      console.log("Email Falso")
      return;
    }
    else{
      // this.emailValido = true;
      this.auth.usuarioAutenticado = true;
    }
    const formObjeto = this.formLogin.getRawValue();

    console.log(formObjeto.email)
    console.log(formObjeto.username)
    
    this.serviceBackend.criaPlayer(formObjeto.username, formObjeto.email).subscribe(
      (data) => {
        this.router.navigate(['/salas']);
      },
      (error) => {
        console.log("Deu erro", error)
      }
    )
  }

}
