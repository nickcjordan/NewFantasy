package com.fantasy.matchupexecutor.model;

import java.util.List;

public class MatchupRequest {
	
	private int weekNumber;
	private List<String> userIds;
	
	public int getWeekNumber() {
		return weekNumber;
	}
	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}
	public List<String> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}
	
}
