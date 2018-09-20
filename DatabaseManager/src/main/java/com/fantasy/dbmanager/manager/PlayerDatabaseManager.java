package com.fantasy.dbmanager.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;

import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dbmanager.dao.PlayerDao;
import com.fantasy.dbmanager.fetcher.PlayerStatsAPIDelegate;
import com.fantasy.dbmanager.model.PlayerStatsAPIResponse;
import com.mongodb.client.FindIterable;

@Component
public class PlayerDatabaseManager {
	
	private static Logger log = Logger.getLogger(PlayerDatabaseManager.class);
	
	@Autowired
	private PlayerDao playerDao;
	
	@Autowired
	private PlayerStatsAPIDelegate statsDelegate;
	
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
		PlayerStatsAPIResponse response = statsDelegate.getUpdatedStats();
		for (Player player : response.getPlayers().values()) {
			if (!updatePlayer(player)) {
				log.info("Could not find player to replace... adding as new: " + player.getPlayerName());
				playerDao.put(player);
			}				
			count++;
		}
		return count;
	}

	public boolean updatePlayer(Player player) {
		 if (playerDao.updatePlayer(player) != null) {
			 log.info("Successfully updates player " + player.getPlayerName());
			 return true;
		 } else {
			 log.info("ERROR trying to update player " + player.getPlayerName());
			 return false;
		 }
	}	

}
