import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageCriarSalaComponent } from './page-criar-sala.component';

describe('PageCriarSalaComponent', () => {
  let component: PageCriarSalaComponent;
  let fixture: ComponentFixture<PageCriarSalaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageCriarSalaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageCriarSalaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
