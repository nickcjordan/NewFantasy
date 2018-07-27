import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {User} from '../../model/user';
import {UserService} from '../../service/user.service';

@Component({
  selector: 'app-user-preview',
  templateUrl: './user-preview.component.html',
  styleUrls: ['./user-preview.component.css']
})
export class UserPreviewComponent implements OnInit {
	
	user: User;
	userId: number;
	

  constructor(
	  private userService: UserService,
	  private route: ActivatedRoute,
  ) {}

	ngOnInit() {
		this.setUser();
	}

	setUser(): void {
		this.userId = +this.route.snapshot.paramMap.get('id');
		this.userService.getUser(this.userId).subscribe(user => this.user = user);
	}

}
