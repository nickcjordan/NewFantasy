package com.fantasy.nfldatafetcher.adapter;

import java.io.File;
import java.io.IOException;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fantasy.nfldatafetcher.model.NFLPlayerSeasonStats;
import com.fantasy.nfldatafetcher.model.advancedstats.APIAdvancedStats;
import com.fantasy.nfldatafetcher.model.basicstats.APIBasicStats;
import com.fantasy.nfldatafetcher.threadrunner.ParentThreadRunnerByWeek;
import com.fantasy.nfldatafetcher.threadrunner.ThreadRunner;
import com.fantasy.nfldatafetcher.threadrunner.consolidator.SeasonStatsMapConsolidator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class NFLDataAdapter extends ThreadRunner {
	
	private static Logger log = Logger.getLogger(NFLDataAdapter.class);
	
	private static final int NUMBER_OF_GAMES_IN_SEASON = 17;

	private static final String CACHED_DATA_PATH = "src/main/resources/stats/getAllPlayerData.json";
	
	@Autowired
	private SeasonStatsMapConsolidator consolidator;
	
	@Value("${data.useCache}")
	private boolean useCachedData;
	
	private Map<String, List<APIAdvancedStats>> advancedWeeklyStatsMap;
	private Map<String, List<APIBasicStats>> basicWeeklyStatsMap;
	private ExecutorService executor;
	
	public NFLDataAdapter() {
		this.advancedWeeklyStatsMap = Collections.synchronizedMap(new HashMap<String, List<APIAdvancedStats>>());
		this.basicWeeklyStatsMap = Collections.synchronizedMap(new HashMap<String, List<APIBasicStats>>());
	}

	public Map<String, NFLPlayerSeasonStats> getPlayerData() {
//		String path = System.getProperty("user.dir");
//		File developmentPath = new File(path + "\\src\\main\\resources\\" + fileName);
		return useCachedData ? getDataFromFile() : retrieveCurrentData();
	}

	private Map<String, NFLPlayerSeasonStats> retrieveCurrentData() {
		log.info("NFLDataAdapter :: useCachedData=" + useCachedData + " :: retrieving current data..." );
		executor = Executors.newCachedThreadPool();
		for (int week = 1; week <= NUMBER_OF_GAMES_IN_SEASON; week++) {
			executeThreads(week);
		}
		waitForThreads(executor);
		log.info("NFLDataFetcher :: all threads have completed :: building playerSeasonStatsMap...");
		return consolidator.buildSeasonStatsMap(basicWeeklyStatsMap, advancedWeeklyStatsMap);
	}

	private Map<String, NFLPlayerSeasonStats> getDataFromFile() {
		log.info("NFLDataAdapter :: useCachedData=" + useCachedData + " :: getting cached data..." );
		Map<String, NFLPlayerSeasonStats> map = null;
		try {
			map = new ObjectMapper().readValue(new File(CACHED_DATA_PATH), new TypeReference<Map<String,NFLPlayerSeasonStats>>(){});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
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
