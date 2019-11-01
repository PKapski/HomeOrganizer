import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {User} from "../user/user";
import {catchError, retry} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseurl = 'http://localhost:8080/users/';

  constructor(private http: HttpClient) {
  }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  postUser(user: User) {
    return this.http.post(this.baseurl, user, this.httpOptions).pipe(retry(1), catchError(this.errorHandler));
  }

  getUser(username: string): Observable<User> {
    return this.http.get<User>(this.baseurl + username, this.httpOptions).pipe(retry(1), catchError(this.errorHandler));
  }

  modifyUser(username: string, user: User) {
    return this.http.patch(this.baseurl + username, user, this.httpOptions).pipe(retry(1), catchError(this.errorHandler));
  }

  getHouseholdUsers(householdId: string): Observable<User[]> {
    return this.http.get<User[]>(this.baseurl + '/household/' + householdId, this.httpOptions).pipe(retry(1), catchError(this.errorHandler));
  }

  errorHandler(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }
}
