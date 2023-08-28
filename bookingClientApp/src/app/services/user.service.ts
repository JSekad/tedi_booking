import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class UserService {

  private apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  public usersMeAitimaEggrafis(): Observable<any>{
    return this.http.get<any>(`${this.apiServerUrl}/user/usersMeAitimaEggrafis`);
  }

}
