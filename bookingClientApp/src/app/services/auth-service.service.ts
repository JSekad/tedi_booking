import {ChangeDetectorRef, Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import jwtDecode from "jwt-decode";
import {Observable, of} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  private apiUrl = 'http://localhost:8080';

  private loggedInUser: any = null;

  getLoggedInUser(): any {
    return this.loggedInUser;
  }

  setLoggedInUser(value: any): void {
    this.loggedInUser = value;
  }



  login(username: string, password: string) {
    const credentials = { username, password };
    return this.http.post<any>(`${this.apiUrl}/api/v1/auth/authenticate`, credentials);
  }

  logoutUser(): void {
    this.clearJwtToken();
    this.loggedInUser = null;
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

  get loggedInUserObservable(): Observable<any | null> {
    if (this.loggedInUser === null) {
      return of(null); // Using the 'of' function from 'rxjs'
    }
    return this.loggedInUser.asObservable();
  }
}
