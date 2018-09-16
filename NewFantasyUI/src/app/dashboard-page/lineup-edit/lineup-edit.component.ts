import { UserCredential } from "../../_models/user-credential";
import { AlertService } from "../../_services/alert.service";
import { Player } from "../../model/player";
import { Component, OnInit, Injectable, Input } from '@angular/core';
import {User} from '../../model/user';
import { AuthService } from "../../service/auth.service";
import {UserService} from '../../service/user.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EditLineupRequest } from "../../model/edit-lineup-request";
import { Router } from "@angular/router";
import { faReply } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-lineup-edit',
  templateUrl: './lineup-edit.component.html',
  styleUrls: ['./lineup-edit.component.scss']
})
export class LineupEditComponent implements OnInit {
	
	faReply = faReply;
	
	@Input() user: User;
	
	constructor(
		private userService: UserService,
		private http: HttpClient,
		private auth: AuthService,
        private alertService: AlertService,
		private router: Router
	) {}

	ngOnInit() {
	}
	
	movePlayerToBench(player: Player) {
		let editLineupRequest = new EditLineupRequest(player.playerId, this.user.userId, "MOVE_TO_BENCH");
		this.userService.editLineup(editLineupRequest).subscribe(
			data => { this.router.navigate(['/']); },
			error => { this.alertService.error(error); }
		);
	}
	
}
