package com.fantasy.dbmanager.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dbmanager.dao.StatsDao;
import com.fantasy.dbmanager.fetcher.StatsFetcherDelegate;
import com.fantasy.dbmanager.fetcher.model.NFLPlayerSeasonStats;
import com.fantasy.dbmanager.model.Player;

@Component
public class StatsDatabaseManager {

	private static Logger log = Logger.getLogger(StatsDatabaseManager.class);
	
	@Autowired
	private StatsFetcherDelegate statsFetcher;
	
	@Autowired
	private StatsDao statsDao;
	
	public int updateAllPlayers() {
		Map<String, NFLPlayerSeasonStats> seasonStatsMap = statsFetcher.getUpdatedStats();
		try {
			return statsDao.updatePlayerData(seasonStatsMap.values());
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

	public Map<String, NFLPlayerSeasonStats> getAll() {
		Map<String, NFLPlayerSeasonStats> map = new HashMap<String, NFLPlayerSeasonStats>();
		for (NFLPlayerSeasonStats stat : statsDao.getAllPlayerStats()) {
			map.put(stat.getId(), stat);
		}
		return map;
	}

	public boolean removeAllStatsFromDB() {
		try {
			return statsDao.removeAllPlayerStats();
		} catch (Exception e) {
			log.error(e);
			return false;
		}
	}

}
