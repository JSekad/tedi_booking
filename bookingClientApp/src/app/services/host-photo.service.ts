import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { HostPhoto } from '../model/host-photo.model';

@Injectable({ providedIn: 'root' })
export class HostPhotoService {

  private apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  public getHostPhoto(id: number): Observable<HostPhoto>{
    return this.http.get<HostPhoto>(`${this.apiServerUrl}/hostPhoto/${id}`);
  }
}