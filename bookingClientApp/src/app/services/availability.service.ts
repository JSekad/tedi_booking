import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Availability } from '../model/availability.model'

@Injectable({ providedIn: 'root' })
export class AvailabilityService {

  private apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  public addNewAvailability(newAvailability: Availability): Observable<any>{
    return this.http.post<Availability>(`${this.apiServerUrl}/availability/add`, newAvailability, {observe: 'response'});
  }

}
