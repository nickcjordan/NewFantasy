package com.fantasy.nfldatafetcher.threadrunner.consolidator;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public Map<String, NFLPlayerSeasonStats> consolidateSeasonStatsMap(Map<String, List<APIBasicStats>> basicWeeklyStatsMap, Map<String, List<APIAdvancedStats>> advancedWeeklyStatsMap) {
		Map<String, NFLPlayerSeasonStats> playerSeasonStatsMap = Collections.synchronizedMap(new HashMap<String, NFLPlayerSeasonStats>());
		populateSeasonStatsWithBasicStats(playerSeasonStatsMap, basicWeeklyStatsMap);
		populateSeasonStatsWithAdvancedStats(playerSeasonStatsMap, advancedWeeklyStatsMap);
		return playerSeasonStatsMap;
	}

	private void populateSeasonStatsWithBasicStats(Map<String, NFLPlayerSeasonStats> playerSeasonStatsMap, Map<String, List<APIBasicStats>> basicWeeklyStatsMap) {
		for (List<APIBasicStats> playerData : basicWeeklyStatsMap.values()) {
			NFLPlayerSeasonStats playerSeasonStats = statsBuilder.buildNewPlayerSeasonStats(playerData.get(0));
			for (APIBasicStats basicWeekStats : playerData) {
				NFLPlayerWeeklyStats weeklyStats = statsBuilder.buildNewWeeklyStat(basicWeekStats, (playerData.indexOf(basicWeekStats) + 1));
				playerSeasonStats.addWeeklyStats(weeklyStats);
				playerSeasonStatsMap.put(playerSeasonStats.getId(), playerSeasonStats);
			}
		}
	}

	private void populateSeasonStatsWithAdvancedStats(Map<String, NFLPlayerSeasonStats> playerSeasonStatsMap, Map<String, List<APIAdvancedStats>> advancedWeeklyStatsMap) {
		for (List<APIAdvancedStats> advancedPlayerData : advancedWeeklyStatsMap.values()) {
			NFLPlayerSeasonStats playerStats = null;
			try {
				playerStats = playerSeasonStatsMap.get(advancedPlayerData.get(0).getId());
				if (playerStats != null) {
					for (APIAdvancedStats advancedWeeklyStats : advancedPlayerData) {
						NFLPlayerWeeklyStats weeklyStats = playerStats.getWeeklyStats().get(advancedPlayerData.indexOf(advancedWeeklyStats) + 1); //offset 0 index
						weeklyStats.setOpponentTeamAbbrev(advancedWeeklyStats.getOpponentTeamAbbr());
						statsBuilder.populatePlayerWeeklyStatsDetails(weeklyStats, advancedWeeklyStats.getStats());
					}
				} else {
					log.error("NFLDataFetcher :: no basic stats found for [" + advancedPlayerData.get(0).getId() + ", " + advancedPlayerData.get(0).getFirstName() + " " + advancedPlayerData.get(0).getLastName() + "]");
				}
			} catch (Exception e) {
				log.error(advancedPlayerData.get(0).getFirstName() + " " + advancedPlayerData.get(0).getLastName(), e);
			}
		}
	}

}
