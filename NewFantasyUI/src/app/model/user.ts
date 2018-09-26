import {Team} from './team';
import {Modifier} from './modifier';

export class User {
	userId: string;
	userName: string;
	coins: number;
	team: Team;
	modifiers: Modifier[];
}

//	private Map<String, Perk> perkTree;
//	private List<Modifier> modifiers;
//	private Map<String, MatchupUserResult> matchupResults;