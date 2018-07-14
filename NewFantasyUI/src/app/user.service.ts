import {Injectable} from '@angular/core';
import {User} from './user';
import {USERS} from './mock-users';
import {Observable, of} from 'rxjs';
import {MessageService} from './message.service';

@Injectable({
	providedIn: 'root'
})
export class UserService {

	constructor(private messageService: MessageService) {}

	//	getUsers(): User[] {
	//		return USERS;
	//}

	getUsers(): Observable<User[]> {
		this.messageService.add('UserService: fetched users');
		return of(USERS);
	}
}
