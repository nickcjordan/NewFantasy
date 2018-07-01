package com.fantasy.matchupexecutor.model.matchup;

import com.fantasy.matchupexecutor.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MatchupResults {
	
	@JsonProperty
	private int weekNumber;
	@JsonProperty
	private User winner;
	@JsonProperty
	private MatchupUserResult winningTeamResults;
	@JsonProperty
	private User loser;
	@JsonProperty
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
	public User getWinner() {
		return winner;
	}
	public void setWinner(User winner) {
		this.winner = winner;
	}
	public MatchupUserResult getWinningTeamResults() {
		return winningTeamResults;
	}
	public void setWinningTeamResults(MatchupUserResult winningTeamResults) {
		this.winningTeamResults = winningTeamResults;
	}
	public User getLoser() {
		return loser;
	}
	public void setLoser(User loser) {
		this.loser = loser;
	}
	public MatchupUserResult getLosingTeamResults() {
		return losingTeamResults;
	}
	public void setLosingTeamResults(MatchupUserResult losingTeamResults) {
		this.losingTeamResults = losingTeamResults;
	}
	
}
