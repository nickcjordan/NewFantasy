import { EditLineupRequest } from "../model/edit-lineup-request";
import {Injectable} from '@angular/core';
import {User} from '../model/user';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {MessageService} from './message.service';
import {catchError, map, tap} from 'rxjs/operators';

@Injectable({
	providedIn: 'root'
})
export class UserService {

	private getAllUsersUrl = '/api/user/getAll';  // URL to web api
	private getUserUrl = '/api/user/get';  // URL to web api
	private updateUserUrl = '/api/user/update';  // URL to web api
	private editUserLineupUrl = '/api/user/lineup/edit';  // URL to web api
	
	
//	private usersUrl = '/api';  // URL to web api
	private httpOptions = {headers: new HttpHeaders({'Content-Type': 'application/json'})};

	constructor(
		private http: HttpClient,
		private messageService: MessageService
	) {}

	/** Log a UserService message with the MessageService */
	private log(message: string) {
		this.messageService.add(`UserService: ${message}`);
		console.info(message)
	}
	
	/** Log list of users */
	private logUsers(users: User[]) {
		users.forEach(function (user) {
			console.log("User: " + user.userId + ", " + user.userName);
		});
	}

	/** GET ALL users */
	getUsers(): Observable<User[]> {
		this.messageService.add('UserService: fetched users');
		return this.http.get<User[]>(this.getAllUsersUrl).pipe(
			tap(users => this.log(`fetched users ${users}`)),
			tap(users => this.logUsers(users)),
			catchError(this.handleError('getUsers', []))
		);
	}

	/** GET user by id. Will 404 if id not found */
	getUser(userId: number): Observable<User> {
		const url = `${this.getUserUrl}/${userId}`;
		return this.http.get<User>(url).pipe(
			tap(_ => this.log(`fetched user userId=${userId}`)),
			catchError(this.handleError<User>(`getUser userId=${userId}`))
		);
	}

	/** POST: update the user on the server */
	updateUser(user: User): Observable<User> {
		return this.http.post<User>(this.updateUserUrl, user, this.httpOptions).pipe(
			tap(_ => this.log(`updated user userId=${user.userId}`)),
			catchError(this.handleError<User>('updateUser userId=${userId}'))
		);
	}

	/** POST: add a new user to the server */
	addUser(user: User): Observable<User> {
		return this.http.post<User>(this.getAllUsersUrl, user, this.httpOptions).pipe(
			tap((user: User) => this.log(`added user w/ userId=${user.userId}`)),
			catchError(this.handleError<User>('addUser'))
		);
	}

	/** DELETE: delete the user from the server */
	deleteUser(u: User | number): Observable<User> {
		var userId = (typeof u === 'number') ? u : u.userId;
		const url = `${this.getAllUsersUrl}/${userId}`;

		return this.http.delete<User>(url, this.httpOptions).pipe(
			tap(_ => this.log(`deleted user userId=${userId}`)),
			catchError(this.handleError<User>('deleteUser'))
		);
	}

	/* GET users whose name contains search term */
	searchUsers(term: string): Observable<User[]> {
		if (!term.trim()) {
			// if not search term, return empty user array.
			return of([]);
		}
		return this.http.get<User[]>(`${this.getAllUsersUrl}/?name=${term}`).pipe(
			tap(_ => this.log(`found users matching "${term}"`)),
			catchError(this.handleError<User[]>('searchUsers', []))
		);
	}
	
	/** POST: edit user's lineup */
	editLineup(req: EditLineupRequest): Observable<EditLineupRequest> {
		return this.http.post<EditLineupRequest>(this.editUserLineupUrl, req, this.httpOptions);
	}
	
	

	/**
 * Handle Http operation that failed. Let the app continue.
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
	private handleError<T>(operation = 'operation', result?: T) {
		return (error: any): Observable<T> => {

			// TODO: send the error to remote logging infrastructure
			console.error(error); // log to console instead

			// TODO: better job of transforming error for user consumption
			this.log(`${operation} failed: ${error.message}`);

			// Let the app keep running by returning an empty result.
			return of(result as T);
		};
	}
}
