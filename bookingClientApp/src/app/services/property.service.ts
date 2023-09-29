import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Property } from '../model/property.model';

@Injectable({ providedIn: 'root' })
export class PropertyService {

  private apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  public getProperty(idOwner: number | null, city: string | null, address: string | null, addressNumber: string | null): Observable<Property>{
    return this.http.get<Property>(`${this.apiServerUrl}/property/searchProperty/${idOwner}/${city}/${address}/${addressNumber}`);
  }

  public addNewProperty(newProperty: any): Observable<any>{
    return this.http.post<any>(`${this.apiServerUrl}/property/newProperty`, newProperty, {observe: 'response'});
  }

  public updateProperty(editProperty: any): Observable<any>{
    return this.http.post<any>(`${this.apiServerUrl}/property/update`, editProperty, {observe: 'response'});
  }

}
