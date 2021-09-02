import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PageMainComponent } from './page-main/page-main.component';
import { PageLoginComponent } from './pages/page-login/page-login.component';
import { PageSalasComponent } from './pages/page-salas/page-salas.component';
import { PageSalaJogandoComponent } from './pages/page-sala-jogando/page-sala-jogando.component';
import { AuthGuardService } from './guards/auth-guard.service';
import { PageCriaSalaComponent } from './pages/page-cria-sala/page-cria-sala.component';



const routes: Routes = [
  { path: '', redirectTo: 'main', pathMatch: 'full' },
  { path: 'main', component: PageLoginComponent, },
  // { path: '**', redirectTo: '' },
  {path: 'salas', component: PageSalasComponent, 
  canActivate: [AuthGuardService]},

  {path: 'jogos', component: PageSalaJogandoComponent},
  {path: 'create', component: PageCriaSalaComponent}
];

@NgModule({
  declarations: [],
  exports: [RouterModule],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ]
})
export class AppRoutingModule { }
