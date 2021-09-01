import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PageMainComponent } from './page-main/page-main.component';
import { PageLoginComponent } from './pages/page-login/page-login.component';
import { PageSalasComponent } from './pages/page-salas/page-salas.component';
import { PageSalaJogandoComponent } from './pages/page-sala-jogando/page-sala-jogando.component';



const routes: Routes = [
  { path: '', redirectTo: '/main', pathMatch: 'full' },
  { path: 'main', component: PageLoginComponent },
  // { path: '**', redirectTo: '' },
  {path: 'salas', component: PageSalasComponent},
  {path: 'jogos', component: PageSalaJogandoComponent}
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
