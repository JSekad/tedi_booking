import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { City } from '../model/city.model';

const httpOptions = {headers: new HttpHeaders({'Accept': 'application/json',
                                               'ContentType': 'application/json'})};

@Injectable({ providedIn: 'root' })
export class CityService {
  private apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  public getAllCities(): Observable<City[]>{
    return this.http.get<City[]>(`${this.apiServerUrl}/city/all`);
  }
}
