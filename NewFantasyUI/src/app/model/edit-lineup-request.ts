export class EditLineupRequest {
	
	playerId: string;
	player2Id: string;
	userId: string;
	action: string;
	
	constructor(pId: string, p2Id: string, uId: string, act: string) { 
		this.playerId = pId;
		this.player2Id = p2Id;
		this.userId = uId;
		this.action = act; 
	}
}