import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import {RouterLink, RouterModule} from "@angular/router";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { MainComponent } from './components/main/main.component';
import {AppRoutingModule} from "./app-routing.module";
import { SearchComponent } from './components/search/search.component';
import { TestComponent } from './components/test/test.component';
import { SearchService } from './services/search.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatDialogModule} from "@angular/material/dialog";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import { LoginDialogComponent } from './components/login-dialog/login-dialog.component';

@NgModule({
  declarations: [
    MainComponent,
    TestComponent,
    SearchComponent,
    LoginDialogComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatButtonModule
  ],
  providers: [SearchService],
  bootstrap: [MainComponent]
})
export class AppModule { }
