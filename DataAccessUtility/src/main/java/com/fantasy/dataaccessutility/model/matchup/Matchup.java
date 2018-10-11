package com.fantasy.dataaccessutility.model.matchup;

import java.util.ArrayList;
import java.util.List;

import com.fantasy.dataaccessutility.model.to.UserTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Matchup {
	
	@JsonProperty
	private String weekNumber;
	@JsonProperty
	private List<String> userIds;
	@JsonProperty
	private MatchupResults results;
	
	public Matchup() {}
	
	public Matchup(String weekNumber) {
		this.weekNumber = weekNumber;
		this.userIds = new ArrayList<String>();
	}
	
	public Matchup(String weekNumber, List<String> userIds) {
		super();
		this.weekNumber = weekNumber;
		this.userIds = userIds;
		this.results = new MatchupResults(Integer.valueOf(weekNumber));
	}
	
	public Matchup(String weekNumber, List<String> userIds, MatchupResults results) {
		super();
		this.weekNumber = weekNumber;
		this.userIds = userIds;
		this.results = results;
	}
	
	public void addUserTO(String u) {
		userIds.add(u);
	}
	
	public String getWeekNumber() {
		return weekNumber;
	}

	public void setWeekNumber(String weekNumber) {
		this.weekNumber = weekNumber;
	}

	public MatchupResults getResults() {
		return results;
	}
	
	public void setResults(MatchupResults results) {
		this.results = results;
	}

	public List<String> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}
	
}
