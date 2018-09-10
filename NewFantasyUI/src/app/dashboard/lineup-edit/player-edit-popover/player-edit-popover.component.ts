import { Component, OnInit, Input } from '@angular/core';
import { UserCredential } from "../../../_models/user-credential";
import { AlertService } from "../../../_services/alert.service";
import { Player } from "../../../model/player";
import {User} from '../../../model/user';
import { EditLineupRequest } from "../../../model/edit-lineup-request";
import { EditLineupQuery } from "../../../model/edit-lineup-query";
import {UserService} from '../../../service/user.service';
import { Router } from "@angular/router";
import { faExchangeAlt } from '@fortawesome/free-solid-svg-icons';




@Component({
  selector: 'app-player-edit-popover',
  templateUrl: './player-edit-popover.component.html',
  styleUrls: ['./player-edit-popover.component.css']
})
export class PlayerEditPopoverComponent implements OnInit {
	
	faExchangeAlt = faExchangeAlt;
	title = "Replace?";
	
	currentUser: UserCredential;
	user: User;
	userId: number;
	swappables: Player[];
	canBeFlex: boolean;
	
	@Input() player: Player;

	constructor(
		private userService: UserService,
		private alertService: AlertService,
		private router: Router
	) { }

	ngOnInit() {
		this.setUser();
//		this.setStartingPlayersToSwapFor();
		this.setIfCanBeFlex();
	}
	
	setIfCanBeFlex(): void {
		this.canBeFlex = false;
		if (this.player.position == "RB" || this.player.position == "WR" || this.player.position == "TE") {
			this.canBeFlex = true;
		}
	}
	
	setUser(): void {
		this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
		this.userService.getUser(this.currentUser.id).subscribe(user => this.user = user);
	}
	
	swapStarterAndBenchPlayer(toLineup: Player, toBench: Player) {
		console.log(`swapping starter and bench player :: ${toLineup.playerName} :: ${toBench.playerName}`);
		let editLineupRequest = new EditLineupRequest(toLineup.playerId, this.user.userId, toBench.playerId);
		this.userService.editLineup(editLineupRequest).subscribe(
			data => { this.router.navigate(['/']); },
			error => { this.alertService.error(error); }
		);
	}
	
	setStartingPlayersToSwapFor() {
		console.log(`getting starting players to swap for :: ${this.player.playerName} :: ${this.user.userName}`);
		let editLineupQuery = new EditLineupQuery(this.player.playerId, this.user.userId);
		this.userService.getListOfPlayersToSwapFor(editLineupQuery).subscribe(
			data => { 
				this.swappables = data;
//				this.router.navigate(['/']); 
			},
			error => { this.alertService.error(error); }
		);
	}
	
	movePlayerToLineupAtPosition(player: Player) {
		let editLineupRequest = new EditLineupRequest(player.playerId, this.user.userId, "START_AT_POSITION");
		this.userService.editLineup(editLineupRequest).subscribe(
			data => { this.router.navigate(['/']); },
			error => { this.alertService.error(error); }
		);
	}
	
	movePlayerToLineupAtFlex(player: Player) {
		let editLineupRequest = new EditLineupRequest(player.playerId, this.user.userId, "START_AT_FLEX");
		this.userService.editLineup(editLineupRequest).subscribe(
			data => { this.router.navigate(['/']); },
			error => { this.alertService.error(error); }
		);
	}

}
