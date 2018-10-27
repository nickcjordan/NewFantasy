package com.fantasy.dataaccessutility.model.matchup;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fantasy.dataaccessutility.model.to.UserTO;
import com.fasterxml.jackson.annotation.JsonProperty;

@DynamoDBDocument
public class Matchup {
	
	private String weekNumber;
	private String userId;
	private String opponentUserId;
	private MatchupResults results;
	
	public Matchup() {}

	public Matchup(String weekNumber, String userId, String opponentUserId) {
		this.weekNumber = weekNumber;
		this.userId = userId;
		this.opponentUserId = opponentUserId;
		this.results = new MatchupResults(Integer.valueOf(weekNumber));
	}

	public String getWeekNumber() {
		return weekNumber;
	}

	public void setWeekNumber(String weekNumber) {
		this.weekNumber = weekNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOpponentUserId() {
		return opponentUserId;
	}

	public void setOpponentUserId(String opponentUserId) {
		this.opponentUserId = opponentUserId;
	}

	public MatchupResults getResults() {
		return results;
	}

	public void setResults(MatchupResults results) {
		this.results = results;
	}
	
	
	
}
