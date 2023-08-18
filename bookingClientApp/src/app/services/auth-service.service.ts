import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import jwtDecode from "jwt-decode";


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  login(username: string, password: string) {
    const credentials = { username, password };
    return this.http.post<any>(`${this.apiUrl}/api/v1/auth/authenticate`, credentials);
  }

  decodeJwtToken(jwt: string): any {
    return jwtDecode(jwt);
  }

  storeJwtToken(jwt: string): void {
    localStorage.setItem('jwt', jwt);
  }

  getStoredJwtToken(): string | null {
    return localStorage.getItem('jwt');
  }

  clearJwtToken(): void {
    localStorage.removeItem('jwt');
  }
}
