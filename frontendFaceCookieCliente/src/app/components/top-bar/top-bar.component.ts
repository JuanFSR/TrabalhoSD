import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.scss']
})
export class TopBarComponent implements OnInit {
  formSala: FormGroup;
  
  constructor(
    private formBuilder: FormBuilder,
  ) { 
    this.formSala = this.formBuilder.group({
      nomeSala: [''],
    });
  }

  ngOnInit(): void {
  }

  newSala() {
    
  }
  enviaNomeSala() {
    const formObjeto = this.formSala.getRawValue();
  }

}
