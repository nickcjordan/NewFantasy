import {MatchupUserResults} from './matchup-user-results';

export class MatchupResults {
	
	weekNumber: string;
	winnerUserId: string;
	winningTeamResults: MatchupUserResults;
	loserUserId: string;
	losingTeamResults: MatchupUserResults;
	
}
