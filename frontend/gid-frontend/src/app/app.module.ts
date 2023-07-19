import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ConsumerRegisterComponent } from './consumer-register/consumer-register.component';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { ConsumerLoginComponent } from './consumer-login/consumer-login.component';
import { ProviderRegisterComponent } from './provider-register/provider-register.component';
import { ProviderLoginComponent } from './provider-login/provider-login.component';


@NgModule({
  declarations: [
    AppComponent,
    ConsumerRegisterComponent,
    HomeComponent,
    ConsumerLoginComponent,
    ProviderRegisterComponent,
    ProviderLoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
