import { Component, OnInit } from '@angular/core';
import {User} from '../model/user';
import {Player} from '../model/player';
import {UserService} from '../service/user.service';
import {ModifierService} from '../service/modifier.service';
import { UserCredential } from "../_models";
import { UserCredentialService } from "../_services/user-credential.service";
import { Modifier } from "../model/modifier";
import { faCoins } from '@fortawesome/free-solid-svg-icons';
import { faArrowAltCircleUp } from '@fortawesome/free-solid-svg-icons';
import { faArrowAltCircleDown } from '@fortawesome/free-solid-svg-icons';
import { faPercent } from '@fortawesome/free-solid-svg-icons';
import {ModifierUpdateRequest} from '../model/modifier-update-request';
import { Router } from "@angular/router";

import { faFootballBall } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-modifier-marketplace-page',
  templateUrl: './modifier-marketplace-page.component.html',
  styleUrls: ['./modifier-marketplace-page.component.scss']
})
export class ModifierMarketplacePageComponent implements OnInit {
	
	faCoins = faCoins;
	faPercent = faPercent; 
	faArrowAltCircleUp = faArrowAltCircleUp;
	faArrowAltCircleDown = faArrowAltCircleDown;
	
	faFootballBall = faFootballBall;
	
	allModifiers: any;
	count: number;
	currentModifier: Modifier;
	
	currentUser: UserCredential;
	userId: number;
	user: User;
	
	
	showAvailable: boolean;
	showPurchased: boolean;
	
	showAll: boolean;
	showByTeam: boolean;
	showByPosition: boolean;
	showBoosters: boolean;
	showNegators: boolean;
	

	constructor(
		private userService: UserService,
		private modifierService: ModifierService,
		private router: Router
	) { 
		this.showAvailable = true;
		this.showPurchased = true;
		this.showAll = true;
		this.showByTeam = true;
		this.showByPosition = true;
		this.showBoosters = true;
		this.showNegators = true;
	}
	
	ngOnInit() {
		this.setUser();
		this.setModifiers();
		this.count = 0;
	}
	
	setUser(): void {
		this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
		this.userService.getUser(this.currentUser.id).subscribe(user => this.user = user);
	}
	
	setModifiers(): void {
		this.modifierService.getAllModifiers().subscribe(allModifiers => this.allModifiers = allModifiers);
	}
	
	buyModifier(selectedModifier: Modifier): void {
		let updateModifierRequest = new ModifierUpdateRequest(this.user.userId, selectedModifier.modifierId, "BUY");
		this.modifierService.sendModifierRequest(updateModifierRequest).subscribe(
			data => { location.reload(); },
			error => { console.log(error) }
		);
	}
	
	sellModifier(selectedModifier: Modifier): void {
		let updateModifierRequest = new ModifierUpdateRequest(this.user.userId, selectedModifier.modifierId, "SELL");
		this.modifierService.sendModifierRequest(updateModifierRequest).subscribe(
			data => { location.reload(); },
			error => { console.log(error) }
		);
	}
	
	setCurrentModifier(mod: Modifier) {
		console.log("setting modifier=[name=" + mod.modifierName + ", id=" + mod.modifierId + "]");
		this.currentModifier = mod;
		document.getElementById("modal_" + mod.modifierId).hidden = false;
	}
	
	//FILTERS
	getFilteredModifiers(): Modifier[] {
		let filtered = this.allModifiers;
		filtered = this.filterByAvailabilityFlags(filtered);
		filtered = this.filterByTargetFlags(filtered);
		filtered = this.filterByTypeFlags(filtered);
		return filtered;
	}
	
	filterByAvailabilityFlags(arr: Modifier[]) {
		return arr.filter((mod: Modifier) => {
			if (mod.owningUserId != null) { return this.showPurchased; }
			if (mod.owningUserId == null) { return this.showAvailable; }
			return false;
		});
	}
	
	filterByTargetFlags(arr: Modifier[]) {
		return arr.filter((mod: Modifier) => {
			if (mod.targetType === "TEAM") { return this.showByTeam; }
			if (mod.targetType === "POSITION") { return this.showByPosition; }
			return false;
		});
	}
	
	filterByTypeFlags(arr: Modifier[]) {
		return arr.filter((mod: Modifier) => {
			if (mod.changePercentage > 0) { return this.showBoosters; }
			if (mod.changePercentage <= 0) { return this.showNegators; }
			return false;
		});
	}
	
	//TOGGLES
	toggleShowAll() { 
		this.showAll = !this.showAll; 
		this.showByTeam = this.showAll;
		this.showByPosition = this.showAll;
		this.showBoosters = this.showAll;
		this.showNegators = this.showAll;
	
	}

	toggleAvailable() { this.showAvailable = !this.showAvailable; }
	togglePurchased() { this.showPurchased = !this.showPurchased; }
	
	toggleShowByTeam() { this.showByTeam = !this.showByTeam; }
	toggleShowByPosition() { this.showByPosition = !this.showByPosition; }
	toggleShowBoosters() { this.showBoosters = !this.showBoosters; }
	toggleShowNegators() { this.showNegators = !this.showNegators; }
}
