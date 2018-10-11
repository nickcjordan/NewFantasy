package com.fantasy.dataaccessutility.model.matchup;

import java.util.List;

public class MatchupTO {
	
	private String weekNumber;
	private String userIdA;
	private String userIdB;
	
	public MatchupTO() {}
	
	public MatchupTO(String weekNumber, String userIdA, String userIdB) {
		this.weekNumber = weekNumber;
		this.userIdA = userIdA;
		this.userIdB = userIdB;
	}
	public String getWeekNumber() {
		return weekNumber;
	}
	public void setWeekNumber(String weekNumber) {
		this.weekNumber = weekNumber;
	}
	public String getUserIdA() {
		return userIdA;
	}
	public void setUserIdA(String userIdA) {
		this.userIdA = userIdA;
	}
	public String getUserIdB() {
		return userIdB;
	}
	public void setUserIdB(String userIdB) {
		this.userIdB = userIdB;
	}
}
