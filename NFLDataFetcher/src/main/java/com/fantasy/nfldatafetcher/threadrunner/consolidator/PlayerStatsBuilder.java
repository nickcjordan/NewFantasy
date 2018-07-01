package com.fantasy.nfldatafetcher.threadrunner.consolidator;

import org.springframework.stereotype.Component;

import com.fantasy.nfldatafetcher.model.NFLPlayerSeasonStats;
import com.fantasy.nfldatafetcher.model.NFLPlayerWeeklyStats;
import com.fantasy.nfldatafetcher.model.advancedstats.APIAdvancedStatsDetails;
import com.fantasy.nfldatafetcher.model.basicstats.APIBasicStats;

@Component
public class PlayerStatsBuilder {
	
	public NFLPlayerWeeklyStats buildNewWeeklyStat(APIBasicStats basicWeekStats, String week) {
		NFLPlayerWeeklyStats weeklyStats = new NFLPlayerWeeklyStats();
		weeklyStats.setWeeklyPoints(basicWeekStats.getWeekPts());
		weeklyStats.setWeekNumber(Integer.valueOf(week));
		return weeklyStats;
	}

	public NFLPlayerSeasonStats buildNewPlayerSeasonStats(APIBasicStats initialPlayerData) {
		NFLPlayerSeasonStats seasonStats = new NFLPlayerSeasonStats();
		seasonStats.setId(initialPlayerData.getId());
		seasonStats.setName(initialPlayerData.getName());
		seasonStats.setPosition(initialPlayerData.getPosition());
		seasonStats.setSeasonTotalPoints(initialPlayerData.getSeasonPts());
		seasonStats.setTeamAbbr(initialPlayerData.getTeamAbbr());
		return seasonStats;
	}

	public void populatePlayerWeeklyStatsDetails(NFLPlayerWeeklyStats weeklyStats, APIAdvancedStatsDetails apiAdvancedStatsDetails) {
		weeklyStats.setCarries(apiAdvancedStatsDetails.getCarries());
		weeklyStats.setFanPtsAgainstOpponentPts(apiAdvancedStatsDetails.getFanPtsAgainstOpponentPts());
		weeklyStats.setFanPtsAgainstOpponentRank(apiAdvancedStatsDetails.getFanPtsAgainstOpponentRank());
		weeklyStats.setReceptions(apiAdvancedStatsDetails.getReceptions());
		weeklyStats.setTargets(apiAdvancedStatsDetails.getTargets());
		weeklyStats.setTouches(apiAdvancedStatsDetails.getTouches());
	}

}
