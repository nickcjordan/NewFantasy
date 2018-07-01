package com.fantasy.matchupexecutor.model.modifier;

import java.util.List;

import com.fantasy.matchupexecutor.model.Player;
import com.fantasy.matchupexecutor.model.Position;

public class PositionGroup {
	
	private List<Player> players;
	private Position position;
	
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	

}
