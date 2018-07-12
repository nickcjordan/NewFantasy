package com.fantasy.matchupexecutor.model;

import java.util.List;

public class MatchupRequest {
	
	private String weekNumber;
	private List<String> userIds;
	
	public String getWeekNumber() {
		return weekNumber;
	}
	public void setWeekNumber(String weekNumber) {
		this.weekNumber = weekNumber;
	}
	public List<String> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}
	
}
