package com.fantasy.dataaccessutility.model.to;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fantasy.dataaccessutility.model.Player;

public class RosterTO {
	
	private StartingLineupTO startingLineup;
	private BenchPlayersTO benchPlayers;
	
	public RosterTO() {
		startingLineup = new StartingLineupTO();
		benchPlayers = new BenchPlayersTO();
	}

	public StartingLineupTO getStartingLineup() {
		return startingLineup;
	}

	public void setStartingLineup(StartingLineupTO startingLineup) {
		this.startingLineup = startingLineup;
	}

	public BenchPlayersTO getBenchPlayers() {
		return benchPlayers;
	}

	public void setBenchPlayers(BenchPlayersTO benchPlayers) {
		this.benchPlayers = benchPlayers;
	}

}
