import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PageMainComponent } from './page-main/page-main.component';
import { BrokerMsgComponent } from './components/broker-msg/broker-msg.component';
import { SubscribeTopicComponent } from './components/subscribe-topic/subscribe-topic.component';
import { BrokerTopicComponent } from './components/broker-topic/broker-topic.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    PageMainComponent,
    BrokerMsgComponent,
    SubscribeTopicComponent,
    BrokerTopicComponent
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
