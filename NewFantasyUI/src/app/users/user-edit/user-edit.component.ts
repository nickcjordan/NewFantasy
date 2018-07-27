import { Component, OnInit } from '@angular/core';
import {User} from '../../model/user';
import {UserService} from '../../service/user.service';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {

	user: User;
	userId: number;
	
	constructor(
		private userService: UserService
	) {}

	ngOnInit() {
		this.setUser();
	}
	
	setUser(): void {
		this.userId = 0; // TODO grab user session id
		this.userService.getUser(this.userId).subscribe(user => this.user = user);
	}
	
	save(): void {
		this.userService.updateUser(this.user).subscribe();
	}
}
