import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { LoginsuccessComponent } from './loginsuccess/loginsuccess.component';
import { RegistrationComponent } from './registration/registration.component';
import { RegistrationsuccessComponent } from './registrationsuccess/registrationsuccess.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginsuccessComponent,
    RegistrationComponent,
    RegistrationsuccessComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
