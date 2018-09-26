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
  styleUrls: ['./player-edit-popover.component.scss']
})
export class PlayerEditPopoverComponent implements OnInit {
	
	faExchangeAlt = faExchangeAlt;
	title = "Replace?";
	
	swappables: Player[];
	canBeFlex: boolean;
	
	@Input() user: User;
	@Input() player: Player;

	constructor(
		private userService: UserService,
		private alertService: AlertService,
		private router: Router
	) { this.router.onSameUrlNavigation = 'reload'; }

	ngOnInit() {
		this.setStartingPlayersToSwapFor();
	}
	
	setStartingPlayersToSwapFor() {
		let pos = this.player.position;
		this.swappables= new Array();
		this.canBeFlex=false;
		if (pos == "QB") { this.addPlayersToSwappables(this.user.team.roster.startingLineup.qb.players); }
		if (pos == "RB") { this.addPlayersToSwappables(this.user.team.roster.startingLineup.rb.players); }
		if (pos == "WR") { this.addPlayersToSwappables(this.user.team.roster.startingLineup.wr.players); }
		if (pos == "TE") { this.addPlayersToSwappables(this.user.team.roster.startingLineup.te.players); }
		if (pos == "K") { this.addPlayersToSwappables(this.user.team.roster.startingLineup.k.players); }
		if (pos == "DST") { this.addPlayersToSwappables(this.user.team.roster.startingLineup.dst.players); }
		if (pos == "RB" || pos == "WR" || pos == "TE") { this.addPlayersToSwappables(this.user.team.roster.startingLineup.flex.players); this.canBeFlex=true;}
	}
	
	addPlayersToSwappables(playerList: Player[]) {
		for (let p of playerList) { this.swappables.push(p); }
	}

	movePlayerToLineupAtPosition(player: Player) {
		let editLineupRequest = new EditLineupRequest(player.playerId, null, this.user.userId, "START_AT_POSITION");
		this.userService.editLineup(editLineupRequest).subscribe(
			data => { location.reload(); }, 			

			error => { this.alertService.error(error); }
		);
	}
		
	swapStarterAndBenchPlayer(toLineup: Player, toBench: Player) {
		let editLineupRequest = new EditLineupRequest(toLineup.playerId, toBench.playerId, this.user.userId, "SWAP");
		this.userService.editLineup(editLineupRequest).subscribe(
			data => { location.reload(); },
			error => { this.alertService.error(error); }
		);
	}
	
	movePlayerToLineupAtFlex(player: Player) {
		let editLineupRequest = new EditLineupRequest(player.playerId, null, this.user.userId, "START_AT_FLEX");
		this.userService.editLineup(editLineupRequest).subscribe(
			data => { location.reload(); },
			error => { this.alertService.error(error); }
		);
	}
	
	dropPlayer(player: Player) {
		let editLineupRequest = new EditLineupRequest(player.playerId, null, this.user.userId, "DROP");
		this.userService.editLineup(editLineupRequest).subscribe(
			data => { location.reload(); },
			error => { this.alertService.error(error); }
		);
	}

}
