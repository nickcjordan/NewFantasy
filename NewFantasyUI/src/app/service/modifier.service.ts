import { Injectable } from '@angular/core';
import {Modifier} from '../model/modifier';
import {User} from '../model/user';
import {ModifierUpdateRequest} from '../model/modifier-update-request';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError, map, tap} from 'rxjs/operators';
import { environment } from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ModifierService {

	private getAllModifiersUrl = `${environment.apiServer}/modifier/getAll`;
	private updateModifierUrl = `${environment.apiServer}/modifier/update`;
	
	private httpOptions = {headers: new HttpHeaders({'Content-Type': 'application/json'})};

	constructor(
		private http: HttpClient,
	) {}

	/** GET ALL modifiers */
	getAllModifiers(): Observable<Modifier[]> {
		return this.http.get<Modifier[]>(this.getAllModifiersUrl).pipe(
			catchError(this.handleError('getAllModifiers', []))
		);
	}
	
	/** POST ALL modifiers */
	sendModifierRequest(req: ModifierUpdateRequest): Observable<ModifierUpdateRequest> {
		return this.http.post<ModifierUpdateRequest>(this.updateModifierUrl, req, this.httpOptions)
	}
	
	private handleError<T>(operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {
			console.error(error); // log to console instead
			return of(result as T);// Let the app keep running by returning an empty result.
		};
	}
}
