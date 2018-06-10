package com.fantasy.dbmanager.builder;

import org.springframework.stereotype.Component;

import com.fantasy.dbmanager.fetcher.model.NFLPlayerSeasonStats;
import com.fantasy.dbmanager.model.Player;
import com.fantasy.dbmanager.model.Position;

@Component
public class PlayerBuilder {

	public Player buildNewPlayer(NFLPlayerSeasonStats stat) {
		Player player = new Player();
		player.setIdentifier(stat.getId());
		player.setPlayerName(stat.getName());
		player.setPosition(stat.getPosition());
		player.setTeamName(stat.getTeamAbbr());
		player.setWeeklyStats(stat.getWeeklyStats());
		return player;
	}

	public Player updatePlayerWithNewStats(Player player, NFLPlayerSeasonStats stat) {
		player.setWeeklyStats(stat.getWeeklyStats());
		return player;
	}

}
