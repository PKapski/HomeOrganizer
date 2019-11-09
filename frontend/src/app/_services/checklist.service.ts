import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError, retry} from "rxjs/operators";
import {Checklist} from "../checklists/checklist";

@Injectable({
  providedIn: 'root'
})
export class ChecklistService {

  baseurl = 'http://localhost:8080/checklists';

  constructor(private http: HttpClient) {
  }

  httpHeader = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  httpOptionsText = {
    headers: this.httpHeader,
    responseType: 'text' as 'text'
  };

  getChecklists(username: string, householdId: string, sortingDirection: string): Observable<Checklist> {
    const params = new HttpParams()
      .set('username', username)
      .set('householdId', householdId)
      .set('sortingDirection', sortingDirection);

    return this.http.get<Checklist>(this.baseurl, {headers: this.httpHeader, params: params}).pipe(retry(1), catchError(this.errorHandler));
  }

  deleteChecklist(id: string) {
    return this.http.delete(this.baseurl + '/' + id, {headers: this.httpHeader}).pipe(retry(1), catchError(this.errorHandler));
  }

  saveChecklist(checklist: Checklist): Observable<string> {
    return this.http.post(this.baseurl, checklist, this.httpOptionsText).pipe(retry(1), catchError(this.errorHandler));
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
