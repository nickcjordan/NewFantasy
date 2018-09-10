export class EditLineupQuery {
	
	playerId: string;
	userId: string;
	
	constructor(pId: string, uId: string) { 
		this.playerId = pId;
		this.userId = uId;
	}
	
}
