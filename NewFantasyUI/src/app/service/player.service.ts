import { Injectable } from '@angular/core';
import {Player} from '../model/player';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError, map, tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

	private getAllPlayersUrl = '/api/player/getAll';  // URL to web api
	
	private httpOptions = {headers: new HttpHeaders({'Content-Type': 'application/json'})};

	constructor(
		private http: HttpClient,
	) {}

	/** GET ALL players */
	getAllPlayers(): Observable<Player[]> {
		return this.http.get<Player[]>(this.getAllPlayersUrl).pipe(
			catchError(this.handleError('getAllPlayers', []))
		);
	}

	private handleError<T>(operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {
			console.error(error); // log to console instead
			return of(result as T);// Let the app keep running by returning an empty result.
		};
	}
	
	
}