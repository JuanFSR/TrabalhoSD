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

@NgModule({
  declarations: [
    AppComponent,
    PageMainComponent,
    BrokerMsgComponent,
    SubscribeTopicComponent,
    BrokerTopicComponent,
    PageLoginComponent,
    PageSalasComponent,
    PageSalaJogandoComponent
  ],
  imports: [
    ReactiveFormsModule,
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
