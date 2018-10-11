package com.fantasy.dataaccessutility.model.matchup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class LeagueMatchupSchedule {
	
	private Map<String, List<MatchupTO>> matchupListsByWeek;

	public LeagueMatchupSchedule(int matchupScheduleLength) {
		matchupListsByWeek = new TreeMap<String, List<MatchupTO>>();
		for (int i = 1; i <= matchupScheduleLength; i++) {
			matchupListsByWeek.put(String.valueOf(i), new ArrayList<MatchupTO>());
		}
	}

	public void addMatchup(String week, String userIdA, String userIdB) {
		 matchupListsByWeek.get(week).add(new MatchupTO(week, userIdA, userIdB));
	}

	public Map<String, List<MatchupTO>> getMatchupTOListsByWeek() {
		return matchupListsByWeek;
	}

	public void setMatchupTOListsByWeek(Map<String, List<MatchupTO>> matchupListsByWeek) {
		this.matchupListsByWeek = matchupListsByWeek;
	}
	
	public boolean lastWeekOfSeasonIsEmpty() {
		return matchupListsByWeek.get(String.valueOf(matchupListsByWeek.size())).isEmpty();
	}
	
}
