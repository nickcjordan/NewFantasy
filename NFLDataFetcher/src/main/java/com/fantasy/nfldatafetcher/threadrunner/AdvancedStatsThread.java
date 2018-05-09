package com.fantasy.nfldatafetcher.threadrunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import org.apache.log4j.Logger;

import com.fantasy.nfldatafetcher.delegate.NFLAdvancedStatsDelegate;
import com.fantasy.nfldatafetcher.model.NFLPlayerSeasonStats;
import com.fantasy.nfldatafetcher.model.NFLPlayerWeeklyStats;
import com.fantasy.nfldatafetcher.model.advancedstats.APIAdvancedStatsResponse;
import com.fantasy.nfldatafetcher.model.advancedstats.APIAdvancedStats;
import com.fantasy.nfldatafetcher.model.basicstats.APIBasicStats;

public class AdvancedStatsThread implements Runnable {
	
	private static Logger log = Logger.getLogger(AdvancedStatsThread.class);
	
	private NFLAdvancedStatsDelegate delegate;

	private Map<String, List<APIAdvancedStats>> advancedWeeklyStatsMap;
	private int week;
	private String pos;

	public AdvancedStatsThread(Map<String, List<APIAdvancedStats>> advancedWeeklyStatsMap, int week, String pos) {
		this.delegate = new NFLAdvancedStatsDelegate();
		this.advancedWeeklyStatsMap = advancedWeeklyStatsMap;
		this.week = week;
		this.pos = pos;
	}

	@Override
	public void run() {
		log.info("NFLDataFetcher :: retrieving advanced weekly stats...");
		APIAdvancedStatsResponse advancedResponse = delegate.getNFLPlayerAdvancedStatsResponse(week, pos);
		for (APIAdvancedStats playerData : advancedResponse.getPlayers()) {
			log.debug("NFLDataFetcher :: populating map with advanced data for player id [" + playerData.getId() + "]");
			populateAdvancedStatsMapWithPlayer(week, playerData);
		}
	}
	
	
	
//	private void populateBasicStatsMapWithPlayer(int week, APIBasicStats playerData) {
//		List<APIBasicStats> basicStatsList = null;
//		if (basicWeeklyStatsMap.containsKey(playerData.getId())) {
//			basicStatsList = basicWeeklyStatsMap.get(playerData.getId());
//		} else {
//			basicStatsList = new ArrayList<APIBasicStats>();
//			basicWeeklyStatsMap.put(playerData.getId(), basicStatsList);
//		}
//		basicStatsList.add(week-1, playerData);
//	}
	

	private void populateAdvancedStatsMapWithPlayer(int week, APIAdvancedStats playerData) {
		List<APIAdvancedStats> advancedStatsList = null;
		if (advancedWeeklyStatsMap.containsKey(playerData.getId())) {
			advancedStatsList = advancedWeeklyStatsMap.get(playerData.getId());
		} else {
			advancedStatsList = new ArrayList<APIAdvancedStats>();
			advancedWeeklyStatsMap.put(playerData.getId(), advancedStatsList);
		}
		advancedStatsList.add(playerData);
	}

//	private void populatePlayerAdvancedStats(APIAdvancedStats playerData, NFLPlayerWeeklyStats weeklyStat) {
//		weeklyStat.setCarries(playerData.getStats().getCarries());
//		weeklyStat.setFanPtsAgainstOpponentPts(playerData.getStats().getFanPtsAgainstOpponentPts());
//		weeklyStat.setFanPtsAgainstOpponentRank(playerData.getStats().getFanPtsAgainstOpponentRank());
//		weeklyStat.setOpponentTeamAbbrev(playerData.getOpponentTeamAbbr());
//		weeklyStat.setReceptions(playerData.getStats().getReceptions());
//		weeklyStat.setTargets(playerData.getStats().getTargets());
//		weeklyStat.setTouches(playerData.getStats().getTouches());
//	}
	
//	private NFLPlayerSeasonStats buildNewPlayerSeasonStats(APIAdvancedStats playerData) {
//		NFLPlayerSeasonStats playerSeasonStats = new NFLPlayerSeasonStats();
//		playerSeasonStats.setId(playerData.getId());
//		playerMap.put(playerSeasonStats.getId(), playerSeasonStats);
//		return playerSeasonStats;
//	}

}
