import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {RouterLink, RouterModule} from "@angular/router";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { MainComponent } from './components/main/main.component';
import {AppRoutingModule} from "./app-routing.module";
import { SearchComponent } from './components/search/search.component';
import { TestComponent } from './components/test/test.component';
import { SearchService } from './services/search.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from "@angular/material/dialog";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { MatButtonModule } from "@angular/material/button";
import { MatTooltipModule } from "@angular/material/tooltip";
import { MAT_DATE_LOCALE, MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from "@angular/material/datepicker";
import { MatFormField } from '@angular/material/form-field';
import { CommonModule } from '@angular/common';
import { MatSnackBarModule} from '@angular/material/snack-bar';
import { ReservationComponent } from './components/reservation/reservation.component';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { NgImageSliderModule } from 'ng-image-slider';

import { AngularOpenlayersModule } from 'ngx-openlayers';
import { OpenstreetmapComponent } from './components/openstreetmap/openstreetmap.component';
import { LoginDialogComponent } from './components/login-dialog/login-dialog.component';
import { JwtInterceptor } from './interceptors/jwt.interceptor';
import {MatMenuModule} from "@angular/material/menu";

@NgModule({
  declarations: [
    MainComponent,
    TestComponent,
    SearchComponent,
    LoginDialogComponent,
    ReservationComponent,
    OpenstreetmapComponent,
  ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        MatDialogModule,
        MatCardModule,
        MatFormFieldModule,
        MatIconModule,
        MatInputModule,
        MatButtonModule,
        MatNativeDateModule,
        MatDatepickerModule,
        MatFormFieldModule,
        CommonModule,
        MatSnackBarModule,
        MatAutocompleteModule,
        NgImageSliderModule,
        MatTooltipModule,
        AngularOpenlayersModule,
        MatMenuModule,
    ],
  providers: [
    SearchService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    },
    {provide: MAT_DATE_LOCALE, useValue: 'en-GB'}
  ],
  bootstrap: [MainComponent]
})
export class AppModule { }
