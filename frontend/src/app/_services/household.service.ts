import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {Household} from "../households/household";
import {catchError, retry} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class HouseholdService {

  baseurl = 'http://localhost:8080/households';

  constructor(private http: HttpClient) { }

  httpHeader = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  getHousehold(householdId: string): Observable<Household>{
    return this.http.get<Household>(this.baseurl+ '/'+ householdId,{headers: this.httpHeader}).pipe(retry(1), catchError(this.errorHandler));
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
