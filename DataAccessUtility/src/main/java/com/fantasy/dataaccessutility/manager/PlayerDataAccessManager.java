package com.fantasy.dataaccessutility.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.DataAccessUtility;
import com.fantasy.dataaccessutility.access.DatabaseManagerDelegate;
import com.fantasy.dataaccessutility.model.Player;

@Component
public class PlayerDataAccessManager {

	@Autowired
	private DatabaseManagerDelegate delegate;

	private static Logger log = Logger.getLogger(PlayerDataAccessManager.class);

	public Player getPlayerById(String playerId) {
		log.info("Getting updated stats for player " + playerId);
		return delegate.getUpdatedPlayer(playerId);
	}

	public Player getPlayerFromPlayer(Player player) {
		return getPlayerById(player.getPlayerId());
	}

	public List<Player> getPlayersByIdList(List<String> playerIds) {
		List<Player> players = new ArrayList<Player>();
		for (String id : playerIds) {
			players.add(getPlayerById(id));
		}
		return players;
	}

	public List<Player> getPlayersFromPlayerList(List<Player> playersRequest) {
		List<Player> players = new ArrayList<Player>();
		for (Player p : playersRequest) {
			players.add(getPlayerById(p.getPlayerId()));
		}
		return players;
	}
	
	public void updatePlayer(Player player) {
		log.info("Updating stats for player " + player.getPlayerId() + " :: " + player.getPlayerName());
		if (!delegate.updatePlayer(player)) {
			log.error("Update player didnt work... trying put...");
			if (delegate.addPlayer(player)) {
				log.info("SUCCESS :: put player in database :: " + player.getPlayerId() + " :: " + player.getPlayerName());
			}
		}
	}
	public void updatePlayers(List<Player> players) {
		for (Player p : players) { updatePlayer(p); }
	}

	public List<Player> getAllPlayersByPosition(String position) {
		return delegate.getUpdatedPositionGroup(position);
	}

}
