package com.fantasy.dbmanager.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;

import com.fantasy.dbmanager.builder.PlayerBuilder;
import com.fantasy.dbmanager.dao.PlayerDao;
import com.fantasy.dbmanager.fetcher.model.NFLPlayerSeasonStats;
import com.fantasy.dbmanager.model.Player;
import com.fantasy.dbmanager.model.Position;
import com.mongodb.client.FindIterable;

@Component
public class PlayerDatabaseManager {
	
	private static Logger log = Logger.getLogger(PlayerDatabaseManager.class);
	
	@Autowired
	private PlayerDao playerDao;
	
	@Autowired
	private StatsDatabaseManager statsManager;
	
	@Autowired
	private PlayerBuilder playerBuilder;

	public void putPlayersInDB(List<Player> players) {
		playerDao.putAll(players);
	}
	
	public long countPlayers() {
		return playerDao.getPlayerCount();
	}
	
	public List<Player> getAll() {
		return convertIterableToList(playerDao.getAll());
	}

	public List<Player> getAll(String position) {
		return convertIterableToList(playerDao.getAllPlayersByPosition(position));
	}
	
	private List<Player> convertIterableToList(FindIterable<Player> results) {
		List<Player> players = new ArrayList<Player>();
		for (Player p : results) {
			players.add(p);
		}
		return players;
	}
	
	public boolean removeAllPlayersFromDB() {
		return playerDao.removeAll();
	}

	public Player get(String val) {
		Player player = null;
		try {
			NumberUtils.parseNumber(val, Integer.class);
			log.info("getting player with id=" + val);
			player = playerDao.getPlayerByID(val);
		} catch (IllegalArgumentException e) {
			log.info("getting player with name=" + val);
			player = playerDao.getPlayerByName(val);
		}
		return player;
	}

	public int updateAll() {
		int count = 0;
		Map<String, NFLPlayerSeasonStats> stats = statsManager.getAll();
		for (NFLPlayerSeasonStats stat : stats.values()) {
			Player player = playerDao.getPlayerByID(stat.getId());
			if (player == null) {
				if (buildAndInsertNewPlayer(stat)) { count++; }
			} else {
				if (updateAndReplacePlayer(player, stat)) { count++; }
			}
		}
		return count;
	}

	private boolean updateAndReplacePlayer(Player player, NFLPlayerSeasonStats stat) {
		Player result = playerDao.updatePlayer(playerBuilder.updatePlayerWithNewStats(player, stat));
		if (result == null) {
			log.error("ERROR :: found player on GET but query found no results trying to UPDATE: " + player.getPlayerName());
			return false;
		} else {
			return true;
		}
	}

	private boolean buildAndInsertNewPlayer(NFLPlayerSeasonStats stat) {
		Player player = playerBuilder.buildNewPlayer(stat);
		try {
			playerDao.put(player);
			log.info("New Player built from updated PlayerSeasonStats list when updating Player database: [" + player.getPlayerName() + " : " + player.getIdentifier() + "]");
			return true;
		} catch (Exception e) {
			log.error("ERROR :: failure to insert new Player when trying to UPDATE: [" + player.getPlayerName() + " : " + Integer.valueOf(player.getIdentifier()) + "]");
			return false;
		}
	}

}
