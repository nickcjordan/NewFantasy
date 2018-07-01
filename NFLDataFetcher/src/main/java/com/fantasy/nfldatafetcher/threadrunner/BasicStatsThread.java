package com.fantasy.nfldatafetcher.threadrunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fantasy.nfldatafetcher.delegate.NFLBasicStatsDelegate;
import com.fantasy.nfldatafetcher.model.basicstats.APIBasicStats;
import com.fantasy.nfldatafetcher.model.basicstats.APIBasicStatsResponse;

public class BasicStatsThread implements Runnable {
	
	private static Logger log = Logger.getLogger(BasicStatsThread.class);
	
	private NFLBasicStatsDelegate delegate;

	private Map<String, Map<String, APIBasicStats>> basicWeeklyStatsMap;
	private String pos;
	private int week;

	public BasicStatsThread(Map<String, Map<String, APIBasicStats>> basicWeeklyStatsMap, int week, String pos) {
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
		Map<String, APIBasicStats> basicStatsList = null;
		if (basicWeeklyStatsMap.containsKey(playerData.getId())) {
			basicStatsList = basicWeeklyStatsMap.get(playerData.getId());
		} else {
			basicStatsList = new HashMap<String, APIBasicStats>();
			basicWeeklyStatsMap.put(playerData.getId(), basicStatsList);
		}
		basicStatsList.put(Integer.toString(week), playerData);
	}
	
}
