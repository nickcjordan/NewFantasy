package com.fantasy.dataaccessutility.model.team;

import static com.fantasy.dataaccessutility.model.team.constants.LineupProperties.BENCH_MAX_SIZE;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fantasy.dataaccessutility.model.Player;

public class BenchPlayers {
	
	private static final Logger log = LoggerFactory.getLogger(BenchPlayers.class);

	private List<Player> players;
	
	public BenchPlayers() {
		players = new ArrayList<Player>();
	}
	
	public boolean addPlayerToBench(Player player) {
//		if (players.size() < BENCH_MAX_SIZE) {
			return players.add(player);
//		} else {
//			log.info("Bench is full :: did not add player: " + player.getPlayerName());
//			return false;
//		}
	}
	
	public boolean removePlayerFromBench(Player player) {
		for (Player p : players) {
			if (p.getPlayerId().equals(player.getPlayerId())) {
				player = p;
				break;
			}
		}
		return players.remove(player);
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

}
