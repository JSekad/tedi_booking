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

@NgModule({
  declarations: [
    MainComponent,
    TestComponent,
    SearchComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [SearchService],
  bootstrap: [MainComponent]
})
export class AppModule { }
