package com.fantasy.dataaccessutility.model.team;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fantasy.dataaccessutility.model.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Roster {
	
	private static final Logger log = LoggerFactory.getLogger(Roster.class);
	
	private StartingLineup startingLineup;
	private BenchPlayers benchPlayers;
	
	public Roster() {
		startingLineup = new StartingLineup();
		benchPlayers = new BenchPlayers();
	}
	
	public void addPlayerToLineupAndMoveReplacedPlayerToBench(Player player) {
		Player removedPlayer = startingLineup.addPlayerToPositionSlot(player);
		if (removedPlayer != null) {
			log.info("Position list " + player.getPosition() + " is full :: removing player " + removedPlayer.getPlayerName() + " from lineup and moving to bench");
			addPlayerToBench(removedPlayer);
		} else {
			log.info("Added player " + player.getPlayerName() + " to starting lineup :: Position [" + player.getPosition()+ "] was not full, no players moved to bench");
		}
	}
	
	public void addFlexPlayerToLineupAndMoveReplacedPlayerToBench(Player player) {
		Player removedPlayer = startingLineup.addPlayerToFlexSlot(player);
		if (removedPlayer != null) {
			log.info("Flex list is full :: removing player " + removedPlayer.getPlayerName() + " from lineup and moving to bench");
			addPlayerToBench(removedPlayer);
		}
	}
	
	public void removePlayerFromLineup(Player player) {
		if (startingLineup.removePlayerFromFromLineup(player)) {
			log.info("Removed player " + player.getPlayerName() + " from lineup");
		} else {
			throw new RuntimeException("ERROR :: could not add player to bench :: bench is full");
		}
	}

	public void addPlayerToBench(Player player) {
		if (benchPlayers.addPlayerToBench(player)) {
			log.info("Added player " + player.getPlayerName() + " to bench :: Bench count = " + benchPlayers.getPlayers().size());
		} else {
			throw new RuntimeException("ERROR :: could not add player to bench :: bench is full");
		}
	}
	
	public void removePlayerFromBench(Player player) {
		if (benchPlayers.removePlayerFromBench(player)) {
			log.info("Removed player " + player.getPlayerName() + " from bench");
		} else {
			log.info("ERROR :: trying to remove player from bench :: player not found on bench: " + player.getPlayerName());
		}
	}

	public void dropPlayerFromRoster(Player player) {
		// TODO Auto-generated method stub
		
	}
	
	@JsonIgnore
	public int getNumberOfPlayersOnTeam() {
		return getAllPlayers().size();
	}
	
	@JsonIgnore
	public List<Player> getAllPlayers() {
		List<Player> players = new ArrayList<Player>();
		players.addAll(startingLineup.getAllStartingPlayers());
		players.addAll(benchPlayers.getPlayers());
		return players;
	}

	public StartingLineup getStartingLineup() {
		return startingLineup;
	}

	public BenchPlayers getBenchPlayers() {
		return benchPlayers;
	}

	public void setStartingLineup(StartingLineup startingLineup) {
		this.startingLineup = startingLineup;
	}

	public void setBenchPlayers(BenchPlayers benchPlayers) {
		this.benchPlayers = benchPlayers;
	}
	
}
