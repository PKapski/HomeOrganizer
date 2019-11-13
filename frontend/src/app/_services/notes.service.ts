import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Note} from "../notes/note";
import {Observable, throwError} from "rxjs";
import {catchError, retry} from "rxjs/operators";
import {ModelPaging} from "../_commons/model/model-paging";

@Injectable({
  providedIn: 'root'
})
export class NotesService {
  baseurl = 'http://localhost:8080/notes';

  constructor(private http: HttpClient) {
  }

  httpHeader = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  httpOptionsText = {
    headers: this.httpHeader,
    responseType: 'text' as 'text'
  };

  getNotes(username: string, householdId: string,firstResult?: number, maxResults?: number, sortingDirection?: string, sortedField?: string): Observable<ModelPaging> {
    let params = new HttpParams()
      .set('username', username)
      .set('householdId', householdId);

    if(firstResult!=null)params=params.append('firstResult',String(firstResult));
    if(maxResults!=null)params=params.append('maxResults',String(maxResults));
    if (sortingDirection != null) params = params.append('sortingDirection', sortingDirection);
    if (sortedField != null) params = params.append('sortedField', sortedField);


    return this.http.get<ModelPaging>(this.baseurl, {headers: this.httpHeader, params: params}).pipe(retry(1), catchError(this.errorHandler));
  }

  deleteNote(id: string) {
    return this.http.delete(this.baseurl + '/' + id, {headers: this.httpHeader}).pipe(retry(1), catchError(this.errorHandler));
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
