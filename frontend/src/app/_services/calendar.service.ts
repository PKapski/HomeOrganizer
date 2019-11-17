import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {CalendarEvent} from 'angular-calendar';
import {catchError, retry} from "rxjs/operators";
@Injectable({
  providedIn: 'root'
})
export class CalendarService {
  baseurl = 'http://localhost:8080/calendar/';

  constructor(private http: HttpClient) {
  }

  httpHeader = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  httpOptionsText = {
    headers: this.httpHeader,
    responseType: 'text' as 'text'
  };
  getCalendarEvents(householdId: string): Observable<CalendarEvent[]>{
    return this.http.get<CalendarEvent[]>(this.baseurl+householdId,{headers: this.httpHeader}).pipe(retry(1), catchError(this.errorHandler));
  }
  saveCalendarEvent(event: CalendarEvent,householdId: string): Observable<string>{
    return this.http.post(this.baseurl+householdId, event, this.httpOptionsText).pipe(retry(1), catchError(this.errorHandler));
  }

  deleteCalendarEvent(id: string){
    return this.http.delete(this.baseurl + id, {headers: this.httpHeader}).pipe(retry(1), catchError(this.errorHandler));
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
