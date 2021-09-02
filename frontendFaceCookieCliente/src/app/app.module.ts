import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PageMainComponent } from './page-main/page-main.component';
import { BrokerMsgComponent } from './components/broker-msg/broker-msg.component';
import { SubscribeTopicComponent } from './components/subscribe-topic/subscribe-topic.component';
import { BrokerTopicComponent } from './components/broker-topic/broker-topic.component';
import { ReactiveFormsModule } from '@angular/forms';
import { PageLoginComponent } from './pages/page-login/page-login.component';
import { PageSalasComponent } from './pages/page-salas/page-salas.component';
import { PageSalaJogandoComponent } from './pages/page-sala-jogando/page-sala-jogando.component';
import { TopBarComponent } from './components/top-bar/top-bar.component';
import { SalaComponentListaComponent } from './components/sala-component-lista/sala-component-lista.component';
import { AuthGuardService } from './guards/auth-guard.service';
import { HttpClientModule } from '@angular/common/http';
import { PageCriarSalaComponent } from './src/app/pages/page-criar-sala/page-criar-sala.component';
import { PageCriaSalaComponent } from './pages/page-cria-sala/page-cria-sala.component';

@NgModule({
  declarations: [
    AppComponent,
    PageMainComponent,
    BrokerMsgComponent,
    SubscribeTopicComponent,
    BrokerTopicComponent,
    PageLoginComponent,
    PageSalasComponent,
    PageSalaJogandoComponent,
    TopBarComponent,
    SalaComponentListaComponent,
    PageCriarSalaComponent,
    PageCriaSalaComponent,
  ],
  imports: [
    ReactiveFormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [PageLoginComponent, AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
