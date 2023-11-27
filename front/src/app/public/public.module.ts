import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PublicRoutingModule } from './public-routing.module';
import { LoginComponent } from '../pages/login/login.component';
import { HomeComponent } from '../pages/home/home.component';
import { RegisterComponent } from '../pages/register/register.component';
import { HeaderRegisterComponent } from '../components/header-register/header-register.component';
import { MatIconModule } from '@angular/material/icon';



@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    HeaderRegisterComponent,
    HomeComponent
  ],
  imports: [
    CommonModule,
    PublicRoutingModule,
    MatIconModule
  ]
})
export class PublicModule { }
