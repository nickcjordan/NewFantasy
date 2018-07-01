package com.fantasy.nfldatafetcher.threadrunner;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fantasy.nfldatafetcher.model.Position;
import com.fantasy.nfldatafetcher.model.advancedstats.APIAdvancedStats;
import com.fantasy.nfldatafetcher.model.basicstats.APIBasicStats;

public class ParentThreadRunnerByWeek extends ThreadRunner implements Runnable {
	
	private Map<String, Map<String, APIBasicStats>> basicWeeklyStatsMap;
	private Map<String, Map<String, APIAdvancedStats>> advancedWeeklyStatsMap;
	private int week;
	private ExecutorService executor;

	public ParentThreadRunnerByWeek(Map<String, Map<String, APIBasicStats>> basicWeeklyStatsMap, Map<String, Map<String, APIAdvancedStats>> advancedWeeklyStatsMap, int week) {
		this.basicWeeklyStatsMap = basicWeeklyStatsMap;
		this.advancedWeeklyStatsMap = advancedWeeklyStatsMap;
		this.week = week;
	}

	@Override
	public void run() {
		this.executor = Executors.newCachedThreadPool();
		for (Position position : Position.values()) {
			executeThreads(position);
		}
		waitForThreads(executor);
	}

	private void executeThreads(Position position) {
		try {
			log.info("NFLDataFetcher :: populating map with data for week [" + week + "] and position [" + position.getAbbrev() + "]");
			executor.execute(new ChildThreadRunnerByPosition(basicWeeklyStatsMap, advancedWeeklyStatsMap, week, position.getAbbrev()));
		} catch (Exception e) {
			log.error("Error populating map for position [" + position.getAbbrev() + "]" , e);
		}
	}
	
}
