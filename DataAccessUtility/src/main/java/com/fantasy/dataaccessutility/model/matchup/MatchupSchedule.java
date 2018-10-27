package com.fantasy.dataaccessutility.model.matchup;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class MatchupSchedule {
	
	private String userId;
	private Map<String, Matchup> scheduleByWeek;
	
	public MatchupSchedule() {}
	
	public MatchupSchedule(String userId) {
		this.scheduleByWeek = new TreeMap<String, Matchup>();
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Map<String, Matchup> getScheduleByWeek() {
		return scheduleByWeek;
	}
	public void setScheduleByWeek(Map<String, Matchup> scheduleByWeek) {
		this.scheduleByWeek = scheduleByWeek;
	}
	
}
