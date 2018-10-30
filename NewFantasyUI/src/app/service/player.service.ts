import { Injectable } from '@angular/core';
import {Player} from '../model/player';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError, map, tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

	private getAllPlayersUrl = `${environment.apiServer}/player/getAll`;
	private getPlayerUrl = `${environment.apiServer}/player/get/`;
	
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
	
	/** GET player by id */
	getPlayer(playerId: number): Observable<Player> {
		const url = `${this.getPlayerUrl}/${playerId}`;
		return this.http.get<Player>(url).pipe(
			catchError(this.handleError<Player>(`getPlayer playerId=${playerId}`))
		);
	}

	private handleError<T>(operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {
			console.error(error); // log to console instead
			return of(result as T);// Let the app keep running by returning an empty result.
		};
	}
	
	
}
