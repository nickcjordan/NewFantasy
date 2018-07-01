package com.fantasy.matchupexecutor.model.matchup;

import java.util.ArrayList;
import java.util.List;

import com.fantasy.matchupexecutor.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Matchup {
	
	@JsonProperty
	private int weekNumber;
	@JsonProperty
	private List<User> users;
	@JsonProperty
	private MatchupResults results;
	
	public Matchup() {}
	
	public Matchup(int weekNumber) {
		this.weekNumber = weekNumber;
		this.users = new ArrayList<User>();
	}
	
	public Matchup(int weekNumber, List<User> users, MatchupResults results) {
		super();
		this.weekNumber = weekNumber;
		this.users = users;
		this.results = results;
	}

	public void addUser(User u) {
		users.add(u);
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public int getWeekNumber() {
		return weekNumber;
	}
	
	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}
	
	public MatchupResults getResults() {
		return results;
	}
	
	public void setResults(MatchupResults results) {
		this.results = results;
	}

}
