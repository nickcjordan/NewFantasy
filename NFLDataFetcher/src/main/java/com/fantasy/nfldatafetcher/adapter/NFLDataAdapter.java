package com.fantasy.nfldatafetcher.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.nfldatafetcher.model.NFLPlayerSeasonStats;
import com.fantasy.nfldatafetcher.model.advancedstats.APIAdvancedStats;
import com.fantasy.nfldatafetcher.model.basicstats.APIBasicStats;
import com.fantasy.nfldatafetcher.threadrunner.ParentThreadRunnerByWeek;
import com.fantasy.nfldatafetcher.threadrunner.ThreadRunner;
import com.fantasy.nfldatafetcher.threadrunner.consolidator.SeasonStatsMapConsolidator;

@Component
public class NFLDataAdapter extends ThreadRunner {
	
	private static Logger log = Logger.getLogger(NFLDataAdapter.class);
	
	private static final int NUMBER_OF_GAMES_IN_SEASON = 17;
	
	@Autowired
	private SeasonStatsMapConsolidator consolidator;
	
	private Map<String, List<APIAdvancedStats>> advancedWeeklyStatsMap;
	private Map<String, List<APIBasicStats>> basicWeeklyStatsMap;
	private ExecutorService executor;
	
	public NFLDataAdapter() {
		this.advancedWeeklyStatsMap = Collections.synchronizedMap(new HashMap<String, List<APIAdvancedStats>>());
		this.basicWeeklyStatsMap = Collections.synchronizedMap(new HashMap<String, List<APIBasicStats>>());
	}

	public Map<String, NFLPlayerSeasonStats> getPlayerData() {
		executor = Executors.newCachedThreadPool();
		for (int week = 1; week <= NUMBER_OF_GAMES_IN_SEASON; week++) {
			executeThreads(week);
		}
		waitForThreads(executor);
		log.info("NFLDataFetcher :: all threads have completed :: building playerSeasonStatsMap...");
		return consolidator.buildSeasonStatsMap(basicWeeklyStatsMap, advancedWeeklyStatsMap);
	}

	private void executeThreads(int week) {
		try {
			log.debug("NFLDataFetcher :: sending week [" + week + "] to ParentThreadRunnerByWeek...");
			executor.execute(new ParentThreadRunnerByWeek(basicWeeklyStatsMap, advancedWeeklyStatsMap, week));
		} catch (Exception e) {
			log.error("Error populating map for week [" + week + "]" , e);
		}
	}
	
}
