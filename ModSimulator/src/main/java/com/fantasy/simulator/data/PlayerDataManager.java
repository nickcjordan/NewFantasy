package com.fantasy.simulator.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.simulator.model.Player;
import com.fantasy.simulator.model.Position;

@Component
public class PlayerDataManager {

	@Autowired
	private PlayerDataDelegate delegate;
	
	private Map<String, List<Player>> playersByPositionMap;
	
	public Map<String, List<Player>> getAllPlayersByPosition() {
		return playersByPositionMap == null ? buildAllPlayersByPositionMap() : playersByPositionMap;
	}
	
	private Map<String, List<Player>> buildAllPlayersByPositionMap() {
		HashMap<String, List<Player>> playersMap = new HashMap<String, List<Player>>();
		for (Position position : Position.values()) {
			playersMap.put(position.getAbbrev(), delegate.getPlayersByPosition(position.getAbbrev()));
		}
		return playersMap;
	}
}
