package com.fantasy.nfldatafetcher.threadrunner.consolidator;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fantasy.nfldatafetcher.adapter.NFLDataAdapter;
import com.fantasy.nfldatafetcher.model.NFLPlayerSeasonStats;
import com.fantasy.nfldatafetcher.model.NFLPlayerWeeklyStats;
import com.fantasy.nfldatafetcher.model.advancedstats.APIAdvancedStats;
import com.fantasy.nfldatafetcher.model.advancedstats.APIAdvancedStatsDetails;
import com.fantasy.nfldatafetcher.model.basicstats.APIBasicStats;

@Component
public class SeasonStatsMapConsolidator {
	
	private static Logger log = Logger.getLogger(SeasonStatsMapConsolidator.class);
	
	public Map<String, NFLPlayerSeasonStats> buildSeasonStatsMap(Map<String, List<APIBasicStats>> basicWeeklyStatsMap, Map<String, List<APIAdvancedStats>> advancedWeeklyStatsMap) {
		Map<String, NFLPlayerSeasonStats> playerSeasonStatsMap = Collections.synchronizedMap(new HashMap<String, NFLPlayerSeasonStats>());
		populateSeasonStatsWithBasicStats(playerSeasonStatsMap, basicWeeklyStatsMap);
		populateSeasonStatsWithAdvancedStats(playerSeasonStatsMap, advancedWeeklyStatsMap);
		return playerSeasonStatsMap;
	}

	private void populateSeasonStatsWithBasicStats(Map<String, NFLPlayerSeasonStats> playerSeasonStatsMap, Map<String, List<APIBasicStats>> basicWeeklyStatsMap) {
		for (List<APIBasicStats> playerData : basicWeeklyStatsMap.values()) {
			NFLPlayerSeasonStats playerSeasonStats = buildNewPlayerSeasonStats(playerData.get(0));
			for (APIBasicStats basicWeekStats : playerData) {
				NFLPlayerWeeklyStats weeklyStats = buildNewWeeklyStat(basicWeekStats, (playerData.indexOf(basicWeekStats) + 1));
				playerSeasonStats.addWeeklyStats(weeklyStats);
				playerSeasonStatsMap.put(playerSeasonStats.getId(), playerSeasonStats);
			}
		}
	}

	private void populateSeasonStatsWithAdvancedStats(Map<String, NFLPlayerSeasonStats> playerSeasonStatsMap, Map<String, List<APIAdvancedStats>> advancedWeeklyStatsMap) {
		for (List<APIAdvancedStats> playerData : advancedWeeklyStatsMap.values()) {
			APIAdvancedStats initial = playerData.get(0);
			NFLPlayerSeasonStats playerSeasonStats = playerSeasonStatsMap.get(initial.getId());
			if (playerSeasonStats != null) {
				for (APIAdvancedStats advancedWeeklyStats : playerData) {
					NFLPlayerWeeklyStats weeklyStats = playerSeasonStats.getWeeklyStats().get(playerData.indexOf(advancedWeeklyStats) + 1);
					weeklyStats.setOpponentTeamAbbrev(advancedWeeklyStats.getOpponentTeamAbbr());
					populatePlayerWeeklyStatsDetails(weeklyStats, advancedWeeklyStats.getStats());
				}
			} else {
				log.error("NFLDataFetcher :: no basic stats found for [" + playerData.get(0).getId() + "]");
			}
		}
	}

	private NFLPlayerWeeklyStats buildNewWeeklyStat(APIBasicStats basicWeekStats, int weekNumber) {
		NFLPlayerWeeklyStats weeklyStats = new NFLPlayerWeeklyStats();
		weeklyStats.setWeeklyPoints(basicWeekStats.getWeekPts());
		weeklyStats.setWeekNumber(weekNumber);
		return weeklyStats;
	}

	private NFLPlayerSeasonStats buildNewPlayerSeasonStats(APIBasicStats initialPlayerData) {
		NFLPlayerSeasonStats seasonStats = new NFLPlayerSeasonStats();
		seasonStats.setId(initialPlayerData.getId());
		seasonStats.setName(initialPlayerData.getName());
		seasonStats.setPosition(initialPlayerData.getPosition());
		seasonStats.setSeasonTotalPoints(initialPlayerData.getSeasonPts());
		seasonStats.setTeamAbbr(initialPlayerData.getTeamAbbr());
		return seasonStats;
	}

	private void populatePlayerWeeklyStatsDetails(NFLPlayerWeeklyStats weeklyStats, APIAdvancedStatsDetails apiAdvancedStatsDetails) {
		weeklyStats.setCarries(apiAdvancedStatsDetails.getCarries());
		weeklyStats.setFanPtsAgainstOpponentPts(apiAdvancedStatsDetails.getFanPtsAgainstOpponentPts());
		weeklyStats.setFanPtsAgainstOpponentRank(apiAdvancedStatsDetails.getFanPtsAgainstOpponentRank());
		weeklyStats.setReceptions(apiAdvancedStatsDetails.getReceptions());
		weeklyStats.setTargets(apiAdvancedStatsDetails.getTargets());
		weeklyStats.setTouches(apiAdvancedStatsDetails.getTouches());
	}

}
