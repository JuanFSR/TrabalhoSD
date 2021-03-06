import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '@app/service/auth.service';
import { BackendServiceService } from '@app/service/backend-service.service';
import { $ } from 'protractor';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.scss']
})
export class TopBarComponent implements OnInit {
  formSala: FormGroup;
  
  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private backendService: BackendServiceService,
    private router: Router,
  ) { 
    this.formSala = this.formBuilder.group({
      nomeSala: [''],
    });
  }

  ngOnInit(): void {
  }

  redireciona() {
    this.router.navigate(['/create']);
  }


}
