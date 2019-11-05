import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Note} from "../notes/note";
import {Observable, throwError} from "rxjs";
import {catchError, retry} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class NotesService {
  baseurl = 'http://localhost:8080/notes';

  constructor(private http: HttpClient) {
  }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  httpHeader= new HttpHeaders({
    'Content-Type': 'application/json'
  });

  httpOptionsText = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    responseType: 'text' as 'text'
  };

  getNotes(username: string, householdId: string, sortingDirection: string): Observable<Note> {
    const params = new HttpParams()
      .set('username',username)
      .set('householdId',householdId)
      .set('sortingDirection',sortingDirection);

    return this.http.get<Note>(this.baseurl, {headers: this.httpHeader, params: params})
      .pipe(
        retry(1),
        catchError(this.errorHandler)
      )
  }

  deleteNote(id: string) {
    return this.http.delete(this.baseurl + '/' + id, this.httpOptions).pipe(retry(1), catchError(this.errorHandler));
  }

  postNote(note: Note): Observable<string> {
    return this.http.post(this.baseurl, note, this.httpOptionsText).pipe(retry(1), catchError(this.errorHandler));
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
