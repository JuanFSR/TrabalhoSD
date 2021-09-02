import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthGuardService } from '@app/guards/auth-guard.service';
import { AuthService } from '@app/service/auth.service';
import { BackendServiceService } from '@app/service/backend-service.service';

@Component({
  selector: 'app-page-cria-sala',
  templateUrl: './page-cria-sala.component.html',
  styleUrls: ['./page-cria-sala.component.scss']
})
export class PageCriaSalaComponent implements OnInit {
  formLogin: FormGroup;

  // emailValido: boolean = true;

  constructor(
    private formBuilder: FormBuilder,
    private serviceBackend: BackendServiceService,
    private router: Router,
    private auth: AuthGuardService,
    private authService: AuthService

  ) { 
    this.formLogin = this.formBuilder.group({
      name: ['', [Validators.required]]
    });
  }

  
  ngOnInit(): void {
  }

  onSubmit() {
    if(this.formLogin.controls.name.errors) {
      console.log("Erro")
      return;
    }
    
    const formObjeto = this.formLogin.getRawValue();

    this.serviceBackend.createSala(formObjeto.name, this.authService.getEmail()).subscribe(
      (data) => {
        this.authService.setId(data.id);
        this.router.navigate(['/jogos']);
      },
      (error) => {
        console.log("Deu erro", error)
      }
    )
  }

}
