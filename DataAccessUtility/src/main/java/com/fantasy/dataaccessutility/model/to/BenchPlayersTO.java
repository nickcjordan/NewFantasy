package com.fantasy.dataaccessutility.model.to;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.Position;

public class BenchPlayersTO {
	
	private List<String> playerIds;
	
	public BenchPlayersTO() {
		playerIds = new ArrayList<String>();
	}
	
	public void addPlayerIdToBenchTO(String playerId) {
		playerIds.add(playerId);
	}

	public List<String> getPlayerIds() {
		return playerIds;
	}

	public void setPlayerIds(List<String> playerIds) {
		this.playerIds = playerIds;
	}
	
}


