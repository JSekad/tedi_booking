import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import jwtDecode from "jwt-decode";
import {Subject} from "rxjs";
import {Router} from "@angular/router";
import {Role} from "../model/role";
import {SnackBarService} from "./snackBar.service";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,private router: Router,private message: SnackBarService) { }

  private apiUrl = 'http://localhost:8080';

  private loggedInUser: any = null;
  loggedInUserChange: Subject<any> = new Subject<any>();

  userRoles: [] = [];
  selectedRole: any = null;

  getLoggedInUser(): any {
    return this.loggedInUser;
  }

  //TODO SELECT
  selectRole(value:any): void{
    this.selectedRole = value;
  }

  refreshPage():void {
    this.refreshtoken().subscribe(
      (response) => {
        console.log("OK1")
        if (response['access_token'] && response['access_token'].length < 100) {
          // The access_token contains the substring "error"
          this.message.warn("Η συνεδρία σας έχει τελειώσει παρακαλώ συνδεθείτε ξανά");
          this.router.navigate(['/']);
        } else {
          // Handle the successful login response here
          console.log("OK3")
          this.storeJwtToken(response['access_token']);
          this.setLoggedInUser(this.decodeJwtToken(response['access_token']));
        }

    this.setLoggedInUser(this.decodeJwtToken(response['access_token']));
  })
  }

  setLoggedInUser(value: any): void {
    //TODO SET USER DETAILS
    this.loggedInUser = JSON.parse(value.sub);
    console.log(this.loggedInUser);
    console.log(this.loggedInUser.roles);
    this.userRoles = this.loggedInUser.roles
    this.selectedRole = this.userRoles.find((obj: {alias: string, name: string}) => {
      if (obj.alias === "owner") return obj;
      if (obj.alias === "user") return obj;
      if (obj.alias === "admin") return obj;
      return null;
    })
    console.log(this.selectedRole);
    this.loggedInUserChange.next(JSON.parse(value.sub));
    if (this.selectedRole.alias === 'admin') this.router.navigate(['/admin']);
  }


  login(username: string, password: string) {
    const credentials = { username, password };
    return this.http.post<any>(`${this.apiUrl}/api/v1/auth/authenticate`, credentials);
  }

  register(credentials: any){
    return this.http.post<any>(`${this.apiUrl}/api/v1/auth/register`, credentials);
  }

  refreshtoken(){
    return this.http.post<any>(`${this.apiUrl}/api/v1/auth/refresh-token`,null)
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
    this.loggedInUser = null;
    this.selectedRole = null;
    this.loggedInUserChange.next(null);
  }

}
