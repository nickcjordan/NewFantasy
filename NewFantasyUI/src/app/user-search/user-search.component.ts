import {Component, OnInit} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {debounceTime, distinctUntilChanged, switchMap} from 'rxjs/operators';
import {User} from '../model/user';
import {UserService} from '../service/user.service';

@Component({
	selector: 'app-user-search',
	templateUrl: './user-search.component.html',
	styleUrls: ['./user-search.component.scss']
})
export class UserSearchComponent implements OnInit {
	users$: Observable<User[]>;
	private searchTerms = new Subject<string>();

	constructor(private userService: UserService) {}

	// Push a search term into the observable stream.
	search(term: string): void {
		this.searchTerms.next(term);
	}

	ngOnInit(): void {
		this.users$ = this.searchTerms.pipe(
			debounceTime(300), // wait 300ms after each keystroke before considering the term
			distinctUntilChanged(), // ignore new term if same as previous term
			switchMap((term: string) => this.userService.searchUsers(term)), // switch to new search observable each time the term changes
		);
	}
}