import { UserCredential } from "../_models";
import { UserCredentialService } from "../_services/user-credential.service";
import { Modifier } from "../model/modifier";
import { ModifierUpdateRequest } from "../model/modifier-update-request";
import {Component, OnInit} from '@angular/core';
import {User} from '../model/user';
import {UserService} from '../service/user.service';
import {AuthService} from '../service/auth.service';
import { ModifierService } from "../service/modifier.service";
import { faCoins, faPercent, faArrowAltCircleUp, faArrowAltCircleDown } from '@fortawesome/free-solid-svg-icons';


@Component({
	selector: 'app-dashboard',
	templateUrl: './dashboard-page.component.html',
	styleUrls: ['./dashboard-page.component.scss']
})
export class DashboardPageComponent implements OnInit {
	
	faCoins = faCoins;
	faPercent = faPercent;
	faArrowAltCircleUp = faArrowAltCircleUp;
	faArrowAltCircleDown = faArrowAltCircleDown;

	currentUser: UserCredential;
	userId: number;
	private user: User;

	constructor(
		private userService: UserService,
		private auth: AuthService,
		private modifierService: ModifierService,
		private credentialService: UserCredentialService
	) {}

	ngOnInit() {
		this.setUser();
	}

	setUser(): void {
		this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
		this.userService.getUser(this.currentUser.id).subscribe(user => this.user = user);
	}
	
	sellModifier(selectedModifier: Modifier): void {
		let updateModifierRequest = new ModifierUpdateRequest(this.user.userId, selectedModifier.modifierId, "SELL");
		this.modifierService.sendModifierRequest(updateModifierRequest).subscribe(
			data => {location.reload();},
			error => {console.log(error)}
		);
	}
	
	getTargetPlayers(selectedModifier: Modifier): Modifier[] {
		//TODO need to do all the schedule processing code so that this piece can know the targets that are on the opponents team
		return null;
	} 

}