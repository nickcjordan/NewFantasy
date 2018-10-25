package com.fantasy.dataaccessutility.model.matchup;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fantasy.dataaccessutility.model.to.UserTO;
import com.fasterxml.jackson.annotation.JsonProperty;

@DynamoDBDocument
public class MatchupResults {
	
	private int weekNumber;
	private String winnerUserId;
	private MatchupUserResult winningTeamResults;
	private String loserUserId;
	private MatchupUserResult losingTeamResults;
	
	public MatchupResults() {}
	
	public MatchupResults(int weekNumber) {
		this.weekNumber = weekNumber;
	}
	public int getWeekNumber() {
		return weekNumber;
	}
	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}
	public String getWinner() {
		return winnerUserId;
	}
	public void setWinner(String winnerUserId) {
		this.winnerUserId = winnerUserId;
	}
	public MatchupUserResult getWinningTeamResults() {
		return winningTeamResults;
	}
	public void setWinningTeamResults(MatchupUserResult winningTeamResults) {
		this.winningTeamResults = winningTeamResults;
	}
	public String getLoser() {
		return loserUserId;
	}
	public void setLoser(String loserUserId) {
		this.loserUserId = loserUserId;
	}
	public MatchupUserResult getLosingTeamResults() {
		return losingTeamResults;
	}
	public void setLosingTeamResults(MatchupUserResult losingTeamResults) {
		this.losingTeamResults = losingTeamResults;
	}
	
}
