import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams, HttpResponse} from "@angular/common/http";
import * as moment from "moment";
import {catchError, map} from "rxjs/operators";
import {throwError} from "rxjs";
import {Router} from "@angular/router";
import {server} from "../../globals";


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  baseurl = server+'users/auth';
  private static router: Router;

  constructor(private http: HttpClient,
              private router: Router) {
    AuthService.router = this.router;
  }
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  authenticate(value: string){
    return this.http.post<any>(this.baseurl,value, {observe: 'response'}).pipe(map((data: HttpResponse<any>)=>this.saveSession(data)),catchError(this.errorHandler));
      //.subscribe((data: HttpResponse<any>)=>this.saveSession(data));
  }

  saveSession(data: HttpResponse<any>){
    const expiresAt = moment().add(data.headers.get("Expiration"));
    localStorage.setItem('auth_token',data.headers.get("Authorization"));
    localStorage.setItem('token_exp_time', JSON.stringify(expiresAt.valueOf()));
    localStorage.setItem('current_user',data.headers.get("Username"));
    if (data.headers.get("Household")!=null) {
      localStorage.setItem('current_household', data.headers.get("Household"));
    }else{
      localStorage.removeItem('current_household');
    }
  }

  public static logout(){
    localStorage.removeItem('auth_token');
    localStorage.removeItem('token_exp_time');
    localStorage.removeItem('current_user');
    localStorage.removeItem('current_household');
    this.router.navigate(['/login'])
  }

  public isLoggedIn() {
    return moment().isBefore(this.getExpiration());
  }

  isLoggedOut() {
    return !this.isLoggedIn();
  }

  getExpiration() {
    const expiration = localStorage.getItem("token_exp_time");
    const expiresAt = JSON.parse(expiration);
    return moment(expiresAt);
  }
  errorHandler(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(error.status);
  }
}
