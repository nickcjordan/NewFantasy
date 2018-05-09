package com.fantasy.nfldatafetcher.threadrunner;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.fantasy.nfldatafetcher.model.advancedstats.APIAdvancedStats;
import com.fantasy.nfldatafetcher.model.basicstats.APIBasicStats;

public class ChildThreadRunnerByPosition extends ThreadRunner implements Runnable {
	
	private static Logger log = Logger.getLogger(ChildThreadRunnerByPosition.class);
	
	private Map<String, List<APIBasicStats>> basicWeeklyStatsMap;
	private Map<String, List<APIAdvancedStats>> advancedWeeklyStatsMap;
	private String pos;
	private int week;
	private ExecutorService executor;

	public ChildThreadRunnerByPosition(Map<String, List<APIBasicStats>> basicWeeklyStatsMap, Map<String, List<APIAdvancedStats>> advancedWeeklyStatsMap, int week, String pos) {
		this.basicWeeklyStatsMap = basicWeeklyStatsMap;
		this.advancedWeeklyStatsMap = advancedWeeklyStatsMap;
		this.week = week;
		this.pos = pos;
	}

	@Override
	public void run() {
		executor = Executors.newCachedThreadPool();
		try {
			executor.execute(new BasicStatsThread(basicWeeklyStatsMap, week, pos));
			executor.execute(new AdvancedStatsThread(advancedWeeklyStatsMap, week, pos));
		} catch (Exception e) {
			log.error("Error populating basic or advanced stats..." , e);
		}
		waitForThreads(executor);
	}
}
