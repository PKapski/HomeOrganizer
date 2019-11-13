import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {User} from "../user/user";
import {catchError, retry} from "rxjs/operators";
import {ModelPaging} from "../_commons/model/model-paging";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseurl = 'http://localhost:8080/users/';

  constructor(private http: HttpClient) {
  }

  httpHeader = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  postUser(user: User) {
    return this.http.post(this.baseurl, user, {headers: this.httpHeader}).pipe(retry(1), catchError(this.errorHandler));
  }

  getUser(username: string): Observable<User> {
    return this.http.get<User>(this.baseurl + username, {headers: this.httpHeader}).pipe(retry(1), catchError(this.errorHandler));
  }

  modifyUser(username: string, user: User) {
    return this.http.patch(this.baseurl + username, user, {headers: this.httpHeader}).pipe(retry(1), catchError(this.errorHandler));
  }

  getHouseholdUsers(householdId: string, firstResult?: number, maxResults?: number, sortingDirection?: string, sortedField?: string): Observable<ModelPaging> {
    let params = new HttpParams();
    if (firstResult != null) params = params.append('firstResult', String(firstResult));
    if (maxResults != null) params = params.append('maxResults', String(maxResults));
    if (sortingDirection != null) params = params.append('sortingDirection', sortingDirection);
    if (sortedField != null) params = params.append('sortedField', sortedField);
    return this.http.get<ModelPaging>(this.baseurl + 'household/' + householdId, {
      headers: this.httpHeader,
      params: params
    }).pipe(retry(1), catchError(this.errorHandler));
  }

  setUserHousehold(username: string, householdId?: string) {
    let params = new HttpParams();
    if (householdId != null) {
      params.set("householdId", householdId);
    }
    return this.http.patch(this.baseurl + username + '/sethousehold', {}, {
      headers: this.httpHeader,
      params: params
    }).pipe(retry(1), catchError(this.errorHandler));
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
