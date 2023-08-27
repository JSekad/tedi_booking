import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import jwtDecode from "jwt-decode";
import {Subject} from "rxjs";
import {Router} from "@angular/router";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,private router: Router) { }

  private apiUrl = 'http://localhost:8080';

  private loggedInUser: any = null;

  loggedInUserChange: Subject<any> = new Subject<any>();

  selectedRole: any = null;

  getLoggedInUser(): any {
    return this.loggedInUser;
  }

  setLoggedInUser(value: any): void {
    //TODO SET USER DETAILS
    this.loggedInUser = JSON.parse(value.sub);
    console.log(this.loggedInUser);
    console.log(this.loggedInUser.roles);
    this.selectedRole = this.loggedInUser.roles.find((obj: {alias: string, name: string}) => {
      const userObject = obj.alias === "user" ? obj : null;
      return userObject ||  obj.alias === "owner" ? obj : null;
    })
    console.log(this.selectedRole);
    this.loggedInUserChange.next(JSON.parse(value.sub));
  }


  login(username: string, password: string) {
    const credentials = { username, password };
    return this.http.post<any>(`${this.apiUrl}/api/v1/auth/authenticate`, credentials);
  }

  register(credentials: any){
    return this.http.post<any>(`${this.apiUrl}/api/v1/auth/register`, credentials);
  }

  logoutUser(): void {
    this.clearJwtToken();
    this.loggedInUser = null;
    this.selectedRole = null;
    this.loggedInUserChange.next(null);
    this.router.navigate(['/'])
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
