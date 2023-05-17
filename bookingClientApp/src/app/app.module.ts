import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { MainComponent } from './components/main/main.component';
import { HttpClientModule } from '@angular/common/http';
import { RoleService } from './services/role.service';
import {RouterLink, RouterModule} from "@angular/router";
import { TestComponent } from './components/test/test.component';
import {AppRoutingModule} from "./app-routing.module";

@NgModule({
  declarations: [
    MainComponent,
    TestComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [RoleService],
  bootstrap: [MainComponent]
})
export class AppModule { }
