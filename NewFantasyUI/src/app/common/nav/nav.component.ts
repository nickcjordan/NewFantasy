import { Component, OnInit } from '@angular/core';
import {User} from '../../model/user';
import {UserService} from '../../service/user.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  users: User[];

	constructor(private userService: UserService) {}

	ngOnInit() {
		this.getUsers();
	}
	
	getUsers(): void {
		this.userService.getUsers().subscribe(users => this.users = users);
	}

}
