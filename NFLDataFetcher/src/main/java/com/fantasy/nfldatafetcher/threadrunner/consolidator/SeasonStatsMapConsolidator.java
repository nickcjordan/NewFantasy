package com.fantasy.nfldatafetcher.threadrunner.consolidator;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.nfldatafetcher.model.NFLPlayerSeasonStats;
import com.fantasy.nfldatafetcher.model.NFLPlayerWeeklyStats;
import com.fantasy.nfldatafetcher.model.advancedstats.APIAdvancedStats;
import com.fantasy.nfldatafetcher.model.basicstats.APIBasicStats;

@Component
public class SeasonStatsMapConsolidator {
	
	private static Logger log = Logger.getLogger(SeasonStatsMapConsolidator.class);
	
	@Autowired
	private PlayerStatsBuilder statsBuilder;
	
	public Map<String, NFLPlayerSeasonStats> consolidateSeasonStatsMap(Map<String, Map<String, APIBasicStats>> basicWeeklyStatsMap, Map<String, Map<String, APIAdvancedStats>> advancedWeeklyStatsMap) {
		Map<String, NFLPlayerSeasonStats> playerSeasonStatsMap = Collections.synchronizedMap(new HashMap<String, NFLPlayerSeasonStats>()); // key is ID
		populateSeasonStatsWithBasicStats(playerSeasonStatsMap, basicWeeklyStatsMap);
		populateSeasonStatsWithAdvancedStats(playerSeasonStatsMap, advancedWeeklyStatsMap);
		return playerSeasonStatsMap;
	}

	private void populateSeasonStatsWithBasicStats(Map<String, NFLPlayerSeasonStats> playerSeasonStatsMap, Map<String, Map<String, APIBasicStats>> basicWeeklyStatsMap) {
		for (Map<String, APIBasicStats> playerData : basicWeeklyStatsMap.values()) { 
			NFLPlayerSeasonStats playerSeasonStats = statsBuilder.buildNewPlayerSeasonStats(playerData.get("1")); // TODO this stuff is broken
			for (Entry<String, APIBasicStats> basicWeekStats : playerData.entrySet()) {
				NFLPlayerWeeklyStats weeklyStats = statsBuilder.buildNewWeeklyStat(basicWeekStats.getValue(), basicWeekStats.getKey());
				playerSeasonStats.addWeeklyStats(weeklyStats);
				playerSeasonStatsMap.put(playerSeasonStats.getId(), playerSeasonStats);
			}
		}
	}

	private void populateSeasonStatsWithAdvancedStats(Map<String, NFLPlayerSeasonStats> playerSeasonStatsMap, Map<String, Map<String, APIAdvancedStats>> advancedWeeklyStatsMap) {
		for (Map<String, APIAdvancedStats> advancedPlayerData : advancedWeeklyStatsMap.values()) {
			NFLPlayerSeasonStats playerStats = null;
			try {
				APIAdvancedStats initial = advancedPlayerData.get("0");
				playerStats = playerSeasonStatsMap.get(initial.getId());
				if (playerStats != null) {
					for (Entry<String, APIAdvancedStats> advancedWeeklyStats : advancedPlayerData.entrySet()) {
						NFLPlayerWeeklyStats weeklyStats = playerStats.getWeeklyStats().get(Integer.valueOf(advancedWeeklyStats.getKey())); 
						weeklyStats.setOpponentTeamAbbrev(advancedWeeklyStats.getValue().getOpponentTeamAbbr());
						statsBuilder.populatePlayerWeeklyStatsDetails(weeklyStats, advancedWeeklyStats.getValue().getStats());
					}
				} else {
					log.error("NFLDataFetcher :: no basic stats found for [" + initial.getId() + ", " + initial.getFirstName() + " " + initial.getLastName() + "]");
				}
			} catch (Exception e) {
				log.error("general error when consolidating stats", e);
			}
		}
	}

}
