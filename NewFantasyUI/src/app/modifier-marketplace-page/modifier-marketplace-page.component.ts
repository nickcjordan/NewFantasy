import { Component, ViewChild, Renderer2, OnInit, ElementRef } from '@angular/core';
import {User} from '../model/user';
import {Player} from '../model/player';
import {UserService} from '../service/user.service';
import {ModifierService} from '../service/modifier.service';
import {UserCredential} from "../_models";
import {UserCredentialService} from "../_services/user-credential.service";
import {Modifier} from "../model/modifier";
import {faCoins} from '@fortawesome/free-solid-svg-icons';
import {faArrowAltCircleUp} from '@fortawesome/free-solid-svg-icons';
import {faArrowAltCircleDown} from '@fortawesome/free-solid-svg-icons';
import {faPercent} from '@fortawesome/free-solid-svg-icons';
import {ModifierUpdateRequest} from '../model/modifier-update-request';
import {Router} from "@angular/router";

import {faFootballBall} from '@fortawesome/free-solid-svg-icons';

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
	
	minPrice: number = 100;
	maxPrice: number = 2000;

	showAvailable: boolean;
	showPurchased: boolean;

	showAll: boolean;
	showByTeam: boolean;
	showByPosition: boolean;
	showBoosters: boolean;
	showNegators: boolean;
	
	sortOptions: Array<any>;
	sortOptionsMapping: Array<any>;
	selectedSortOption: string;
	selectedValue: any;
	reverseSort: boolean;
        

	@ViewChild('minInput') minInput: ElementRef;
	@ViewChild('maxInput') maxInput: ElementRef;
	@ViewChild('minRangeCloud') minRangeCloud: ElementRef;
	@ViewChild('maxRangeCloud') maxRangeCloud: ElementRef;
	minRange: any = 100;
	maxRange: any = 2000;


	constructor(
		private userService: UserService,
		private modifierService: ModifierService,
		private router: Router,
		private renderer: Renderer2
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
		this.sortOptions = [ 
			{ value: '0', label: 'Price (low to high)' }, 
			{ value: '1', label: 'Price (high to low)' }, 
			{ value: '2', label: 'Percentage (low to high)' }, 
			{ value: '3', label: 'Percentage (high to low)' } 
		];
		this.sortOptionsMapping = ["price", "price", "changePercentage", "changePercentage"];
		this.getSelectedValue(0);
	}

	setUser(): void {
		this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
		this.userService.getUser(this.currentUser.id).subscribe(user => this.user = user);
	}

	setModifiers(): void {
		this.modifierService.getAllModifiers().subscribe(allModifiers => this.allModifiers = allModifiers);
	}

	buyModifier(selectedModifier: Modifier): void {
		let updateModifierRequest = new ModifierUpdateRequest(this.user.userId, selectedModifier.modifierId, null, "BUY");
		this.modifierService.sendModifierRequest(updateModifierRequest).subscribe(
			data => {location.reload();},
			error => {console.log(error)}
		);
	}

	sellModifier(selectedModifier: Modifier): void {
		let updateModifierRequest = new ModifierUpdateRequest(this.user.userId, selectedModifier.modifierId, null, "SELL");
		this.modifierService.sendModifierRequest(updateModifierRequest).subscribe(
			data => {location.reload();},
			error => {console.log(error)}
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
		filtered = this.filterByPriceSlider(filtered);
		filtered = this.sortMods(filtered);
		return filtered;
	}
	
	filterByPriceSlider(arr: Modifier[]) {
		if ((arr == null) || (arr.length == 0)) {return arr;}
		return arr.filter((mod: Modifier) => {
			return ((mod.price >= this.minRange) && (mod.price <= this.maxRange));
		});
	}

	filterByAvailabilityFlags(arr: Modifier[]) {
		if ((arr == null) || (arr.length == 0)) {return arr;}
		return arr.filter((mod: Modifier) => {
			if (mod.owningUserId != null) {return this.showPurchased;}
			if (mod.owningUserId == null) {return this.showAvailable;}
			return false;
		});
	}

	filterByTargetFlags(arr: Modifier[]) {
		if ((arr == null) || (arr.length == 0)) {return arr;}
		return arr.filter((mod: Modifier) => {
			if (mod.targetType === "TEAM") {return this.showByTeam;}
			if (mod.targetType === "POSITION") {return this.showByPosition;}
			return false;
		});
	}

	filterByTypeFlags(arr: Modifier[]) {
		if ((arr == null) || (arr.length == 0)) {return arr;}
		return arr.filter((mod: Modifier) => {
			if (mod.changePercentage > 0) {return this.showBoosters;}
			if (mod.changePercentage <= 0) {return this.showNegators;}
			return false;
		});
	}
	
	// SORT
	sortMods(mods: Modifier[]): Modifier[] {
		let by: string = this.selectedSortOption;
		mods.sort((a: any, b: any) => {
			if (a[by] < b[by]) { return (this.reverseSort) ? 1 : -1; }
			if (a[by] >= b[by]) { return (this.reverseSort) ? -1 : 1; }
			return 0;
		});
		return mods;
	}
	
	getSelectedValue(val: number) {
		console.log("val=" + val);
		this.reverseSort = ((val == 1) || (val == 3));
		console.log("reverseSort=" + this.reverseSort);
		this.selectedSortOption = this.sortOptionsMapping[val];
	}
	
	//TOGGLES
	toggleShowAll() {
		this.showAll = !this.showAll;
		this.showByTeam = this.showAll;
		this.showByPosition = this.showAll;
		this.showBoosters = this.showAll;
		this.showNegators = this.showAll;

	}

	toggleAvailable() {this.showAvailable = !this.showAvailable;}
	togglePurchased() {this.showPurchased = !this.showPurchased;}

	toggleShowByTeam() {this.showByTeam = !this.showByTeam;}
	toggleShowByPosition() {this.showByPosition = !this.showByPosition;}
	toggleShowBoosters() {this.showBoosters = !this.showBoosters;}
	toggleShowNegators() {this.showNegators = !this.showNegators;}
	
	
	minCoverage() {
//		let minCloudRange = (this.minRange / this.maxPrice) * 100;
//		this.renderer.setStyle(this.minRangeCloud.nativeElement, 'left', minCloudRange + '%')
	}
	
	maxCoverage() {
//		let maxCloudRange = (this.maxRange / this.maxPrice) * 100;
//		this.renderer.setStyle(this.maxRangeCloud.nativeElement, 'left', maxCloudRange + '%')
	}
}
