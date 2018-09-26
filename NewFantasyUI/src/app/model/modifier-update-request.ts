export class ModifierUpdateRequest {
	
	userId: string;
	modifierId: string;
	action: string;
	
	constructor(userId: string, modifierId: string, action: string) { 
		this.modifierId = modifierId;
		this.userId = userId;
		this.action = action; 
	}
}
