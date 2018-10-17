export class ModifierUpdateRequest {
	
	userId: string;
	modifierId: string;
	playerId: string;
	action: string;
	
	constructor(userId: string, modifierId: string, playerId: string, action: string) { 
		this.userId = userId;
		this.modifierId = modifierId;
		this.playerId = playerId;
		this.action = action; 
	}
}
