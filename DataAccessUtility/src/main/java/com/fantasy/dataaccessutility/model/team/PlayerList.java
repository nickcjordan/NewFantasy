package com.fantasy.dataaccessutility.model.team;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.fantasy.dataaccessutility.model.Player;

public class PlayerList {

	private int max;
	private LinkedList<Player> players;

	public PlayerList() {}

	public PlayerList(int max) {
		this.max = max;
		this.players = new LinkedList<Player>();
	}

	// Adds a player to this list at lowest index --> If list is full, player at
	// highest index gets removed and returned
	public Player addPlayer(Player playerToAdd) {
		players.addFirst(playerToAdd);
		return (players.size() > max) ? players.removeLast() : null;
	}

	public boolean removePlayer(Player playerToRemove) {
		for (Player p : players) {
			if (p.getPlayerId().equals(playerToRemove.getPlayerId())) {
				playerToRemove = p;
				break;
			}
		}
		return players.remove(playerToRemove);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public void setPlayers(LinkedList<Player> players) {
		this.players = players;
	}
	
	public boolean containsPlayer(Player p) {
		Iterator<Player> i = players.iterator();
		while (i.hasNext()) {
			if (i.next().getPlayerId().equals(p.getPlayerId())) {
				return true;
			}
		}
		return false;
	}

}
