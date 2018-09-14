import { Component, OnInit, Input } from '@angular/core';
import {Player} from '../../model/player';
import {User} from '../../model/user';
import {UserService} from '../../service/user.service';
import { EditLineupRequest } from "../../model/edit-lineup-request";
import { Router } from "@angular/router";

import { faUserPlus } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'player-table',
  templateUrl: './player-table.component.html',
  styleUrls: ['./player-table.component.scss']
})
export class PlayerTableComponent implements OnInit {
	
	faUserPlus = faUserPlus;
	@Input() players: Player[];
	@Input() user: User;
	
	@Input() showAvailablePlayers: boolean;
	@Input() showTakenPlayers: boolean;
	
	constructor(
		private userService: UserService,
		private router: Router
	) { }
	
	ngOnInit() {
	}
	
	addPlayerToBench(player: Player) {
		let editLineupRequest = new EditLineupRequest(player.playerId, this.user.userId, "ADD_TO_BENCH");
		this.userService.editLineup(editLineupRequest).subscribe(
			data => { this.router.navigate(['/players']); },
		);
	}

}
