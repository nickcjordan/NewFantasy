package com.fantasy.nfldatafetcher.threadrunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import org.apache.log4j.Logger;

import com.fantasy.nfldatafetcher.delegate.NFLAdvancedStatsDelegate;
import com.fantasy.nfldatafetcher.delegate.NFLBasicStatsDelegate;
import com.fantasy.nfldatafetcher.model.NFLPlayerSeasonStats;
import com.fantasy.nfldatafetcher.model.NFLPlayerWeeklyStats;
import com.fantasy.nfldatafetcher.model.advancedstats.APIAdvancedStatsResponse;
import com.fantasy.nfldatafetcher.model.advancedstats.APIAdvancedStats;
import com.fantasy.nfldatafetcher.model.basicstats.APIBasicStatsResponse;
import com.fantasy.nfldatafetcher.model.basicstats.APIBasicStats;

public class BasicStatsThread implements Runnable {
	
	private static Logger log = Logger.getLogger(BasicStatsThread.class);
	
	private NFLBasicStatsDelegate delegate;

	private Map<String, List<APIBasicStats>> basicWeeklyStatsMap;
	private String pos;
	private int week;

	public BasicStatsThread(Map<String, List<APIBasicStats>> basicWeeklyStatsMap, int week, String pos) {
		this.basicWeeklyStatsMap = basicWeeklyStatsMap;
		this.week = week;
		this.pos = pos;
		this.delegate = new NFLBasicStatsDelegate();
	}

	@Override
	public void run() {
		log.info("NFLDataFetcher :: retrieving basic weekly stats...");
		APIBasicStatsResponse basicResponse = delegate.getNFLPlayerBasicStatsResponse(week, pos);
		for (APIBasicStats basicStats : basicResponse.getPlayers()) {
			log.debug("NFLDataFetcher :: populating map with basic data for player [" + basicStats.getName() + "]");
			populateBasicStatsMapWithPlayer(week, basicStats);
		}
	}

	private void populateBasicStatsMapWithPlayer(int week, APIBasicStats playerData) {
		List<APIBasicStats> basicStatsList = null;
		if (basicWeeklyStatsMap.containsKey(playerData.getId())) {
			basicStatsList = basicWeeklyStatsMap.get(playerData.getId());
		} else {
			basicStatsList = new ArrayList<APIBasicStats>();
			basicWeeklyStatsMap.put(playerData.getId(), basicStatsList);
		}
		basicStatsList.add(playerData);
	}
	
	
//		NFLPlayerSeasonStats playerSeasonStats = new NFLPlayerSeasonStats();
//		playerSeasonStats.setId(playerData.getId());
//		playerSeasonStats.setName(playerData.getName());
//		playerSeasonStats.setPosition(playerData.getPosition());
//		playerSeasonStats.setTeamAbbr(playerData.getTeamAbbr());
//		playerMap.put(playerSeasonStats.getId(), playerSeasonStats);
//		return playerSeasonStats;
	
	
	
	
	//advanced
	
//	private void addToPlayerDataInMap(int week, APIAdvancedStats playerData) {
//		NFLPlayerSeasonStats playerSeasonStats = (playerMap.containsKey(playerData.getId())) ? playerMap.get(playerData.getId()) : buildNewPlayerSeasonStats(playerData);
//		
//		NFLPlayerWeeklyStats weeklyStat = null;
//		if (playerSeasonStats.getWeeklyStats().containsKey(week)) {
//			weeklyStat = playerSeasonStats.getWeeklyStats().get(week);
//		} else {
//			weeklyStat = new NFLPlayerWeeklyStats();
//			weeklyStat.setWeekNumber(week);
//			playerSeasonStats.getWeeklyStats().put(week, weeklyStat);
//		}
//		
//		populatePlayerAdvancedStats(playerData, weeklyStat);
//		
//		NFLPlayerSeasonStats populated = playerMap.get(playerData.getId());
//		NFLPlayerWeeklyStats stat = populated.getWeeklyStats().get(week);
//		System.out.println();
//	}
//
//	//TODO do this later down the line, once advanced map has been populated
//	private void populatePlayerAdvancedStats(APIAdvancedStats playerData, NFLPlayerWeeklyStats weeklyStat) {
//		weeklyStat.setCarries(playerData.getStats().getCarries());
//		weeklyStat.setFanPtsAgainstOpponentPts(playerData.getStats().getFanPtsAgainstOpponentPts());
//		weeklyStat.setFanPtsAgainstOpponentRank(playerData.getStats().getFanPtsAgainstOpponentRank());
//		weeklyStat.setOpponentTeamAbbrev(playerData.getOpponentTeamAbbr());
//		weeklyStat.setReceptions(playerData.getStats().getReceptions());
//		weeklyStat.setTargets(playerData.getStats().getTargets());
//		weeklyStat.setTouches(playerData.getStats().getTouches());
//	}
//	
//	private NFLPlayerSeasonStats buildNewPlayerSeasonStats(APIAdvancedStats playerData) {
//		NFLPlayerSeasonStats playerSeasonStats = new NFLPlayerSeasonStats();
//		playerSeasonStats.setId(playerData.getId());
//		playerMap.put(playerSeasonStats.getId(), playerSeasonStats);
//		return playerSeasonStats;
//	}

}
