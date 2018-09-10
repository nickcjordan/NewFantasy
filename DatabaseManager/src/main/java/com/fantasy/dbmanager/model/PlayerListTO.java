package com.fantasy.dbmanager.model;

import java.util.ArrayList;
import java.util.List;

public class PlayerListTO {
	
	private List<String> playerIds;
	
	public PlayerListTO() {
		this.playerIds = new ArrayList<String>();
	}
	
	public void addPlayerId(String playerId) {
		playerIds.add(playerId);
	}

	public List<String> getPlayerIds() {
		return playerIds;
	}

	public void setPlayerIds(List<String> playerIds) {
		this.playerIds = playerIds;
	}
	
}
