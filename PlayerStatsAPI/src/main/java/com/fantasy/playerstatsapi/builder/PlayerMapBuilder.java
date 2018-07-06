package com.fantasy.playerstatsapi.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fantasy.playerstatsapi.api.PlayerStatsAPIDelegate;
import com.fantasy.playerstatsapi.api.ThreadRunner;
import com.fantasy.playerstatsapi.model.Player;
import com.fantasy.playerstatsapi.model.api.PlayerMetaData;
import com.fantasy.playerstatsapi.model.api.RawPlayerStats;
import com.fantasy.playerstatsapi.model.api.WeekStatsResponse;

@Component
public class PlayerMapBuilder {
	
	private static Logger log = Logger.getLogger(PlayerMapBuilder.class);
	
	@Value("${data.weeksInSeason}")
	private String weeksInSeason;
	
	@Autowired
	private PlayerStatsAPIDelegate delegate;

	@Autowired
	private PositionStatsDetailsBuilder statsBuilder;
	
	public Map<String, Player> buildAndPopulatePlayerMap() {
		Map<String, Player> players = buildPlayerMapWithRawStats();
		List<Player> playersToRemove = Collections.synchronizedList(new ArrayList<Player>());
		
		log.info("Initiating iterative threads...");
		Iterator<Player> iterator = players.values().iterator();
		int count = 0;
		ExecutorService executor = Executors.newCachedThreadPool();
		
		while (iterator.hasNext()) {
			count++;
			Player player = iterator.next();
			executor.execute(new ThreadRunner(playersToRemove, delegate, player));
			if (count >= 100) {
				waitForThreads(executor);
				executor = Executors.newCachedThreadPool();
				count = 0;
			}
		}
		waitForThreads(executor);
		
		for (Player playerToRemove : playersToRemove) {
			players.remove(playerToRemove.getPlayerId());
		}
		return players;
	}
	
	private Map<String, Player> buildPlayerMapWithRawStats() {
		Map<String, Player> players = new HashMap<String, Player>();
		for (int week = 1; week <= Integer.valueOf(weeksInSeason); week++) {
			populatePlayerMapWithWeeklyStats(players, delegate.retrieveAllWeekStats(week));
		}
		log.info("Retrieved all weeklyStats");
		return players;
	}

	private void populatePlayerMapWithWeeklyStats(Map<String, Player> players, WeekStatsResponse response) {
		for (Entry<String, RawPlayerStats> entry : response.getStats().entrySet()) {
			Player player = (players.containsKey(entry.getKey())) ? players.get(entry.getKey()) : new Player(entry.getKey());
			player.getStatsByWeek().put(Integer.toString(response.getWeekNumber()), statsBuilder.buildPositionStatsDetails(entry.getValue()));
			players.put(player.getPlayerId(), player);
		}
	}
	
	private void waitForThreads(ExecutorService executor) {
		executor.shutdown();
		try {
			executor.awaitTermination(30, TimeUnit.SECONDS);
			log.info("All threads have returned");
		} catch (InterruptedException e) {
			log.error(e);
		}
	}

}
