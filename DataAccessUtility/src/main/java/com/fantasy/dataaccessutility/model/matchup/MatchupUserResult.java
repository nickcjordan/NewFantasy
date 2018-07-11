package com.fantasy.dataaccessutility.model.matchup;

import java.util.ArrayList;
import java.util.List;

import com.fantasy.dataaccessutility.model.modifier.Modifier;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MatchupUserResult {
	
	@JsonProperty
	private int week;
	@JsonProperty
	private int coinsEarned;
	@JsonProperty
	private boolean winner;
	@JsonProperty
	private double totalPointsScored;
	@JsonProperty
	private List<Modifier> modifiersApplied;
	@JsonProperty
	private String opponentId;
	
	private String matchupUserResultId;
	
	public MatchupUserResult() {}
	
	public MatchupUserResult(int week) {
		this.week = week;
		this.modifiersApplied = new ArrayList<Modifier>();
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public int getCoinsEarned() {
		return coinsEarned;
	}
	public void setCoinsEarned(int coinsEarned) {
		this.coinsEarned = coinsEarned;
	}
	public boolean isWinner() {
		return winner;
	}
	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	public double getTotalPointsScored() {
		return totalPointsScored;
	}
	public void setTotalPointsScored(double totalPointsScored) {
		this.totalPointsScored = totalPointsScored;
	}
	public List<Modifier> getModifiersApplied() {
		return modifiersApplied;
	}
	public void setModifiersApplied(List<Modifier> modifiersApplied) {
		this.modifiersApplied = modifiersApplied;
	}
	public String getOpponentId() {
		return opponentId;
	}
	public void setOpponentId(String opponentId) {
		this.opponentId = opponentId;
	}

	public String getMatchupUserResultId() {
		return matchupUserResultId;
	}

	public void setMatchupUserResultId(String matchupUserResultId) {
		this.matchupUserResultId = matchupUserResultId;
	}
	
}
