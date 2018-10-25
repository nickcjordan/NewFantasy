package com.fantasy.dataaccessutility.model.team;

import static com.fantasy.dataaccessutility.model.team.constants.LineupProperties.DST_MAX_SIZE;
import static com.fantasy.dataaccessutility.model.team.constants.LineupProperties.FLEX_MAX_SIZE;
import static com.fantasy.dataaccessutility.model.team.constants.LineupProperties.K_MAX_SIZE;
import static com.fantasy.dataaccessutility.model.team.constants.LineupProperties.QB_MAX_SIZE;
import static com.fantasy.dataaccessutility.model.team.constants.LineupProperties.RB_MAX_SIZE;
import static com.fantasy.dataaccessutility.model.team.constants.LineupProperties.TE_MAX_SIZE;
import static com.fantasy.dataaccessutility.model.team.constants.LineupProperties.WR_MAX_SIZE;

import java.util.ArrayList;
import java.util.List;

import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class StartingLineup {
	
	private PlayerList qb;
	private PlayerList rb;
	private PlayerList wr;
	private PlayerList te;
	private PlayerList flex;
	private PlayerList k;
	private PlayerList dst;

	public StartingLineup() {
		this.qb = new PlayerList(QB_MAX_SIZE);
		this.rb = new PlayerList(RB_MAX_SIZE);
		this.wr = new PlayerList(WR_MAX_SIZE);
		this.te = new PlayerList(TE_MAX_SIZE);
		this.flex = new PlayerList(FLEX_MAX_SIZE);
		this.k = new PlayerList(K_MAX_SIZE);
		this.dst = new PlayerList(DST_MAX_SIZE);
	}

	@JsonIgnore
	public Player addPlayerToPositionSlot(Player player) {
		return getListFromPlayerPosition(player.getPlayerPosition()).addPlayer(player);
	}
	
	@JsonIgnore
	public Player addPlayerToFlexSlot(Player player) {
		return flex.addPlayer(player);
	}

	// Tries to remove from position group :: tries  to remove from flex if first remove fails
	public boolean removePlayerFromFromLineup(Player player) {
		return removePlayerFromFromFlex(player) || removePlayerFromFromPosition(player);
	}
	
	public boolean removePlayerFromFromFlex(Player player) {
		return (flex.removePlayer(player));
	}
	
	public boolean removePlayerFromFromPosition(Player player) {
		return (getListFromPlayerPosition(player.getPlayerPosition()).removePlayer(player));
	}
	
	private PlayerList getListFromPlayerPosition(String position) {
		switch (Position.get(position)) {
			case QUARTERBACK: return this.qb;
			case RUNNINGBACK: return this.rb;
			case WIDERECEIVER: return this.wr;
			case TIGHTEND: return this.te;
			case KICKER: return this.k;
//			case DEFENSE: return this.dst;
		}
		return null;
	}

	@JsonIgnore
	public List<Player> getAllStartingPlayers() {
		List<Player> players = new ArrayList<Player>();
		players.addAll(qb.getPlayers());
		players.addAll(rb.getPlayers());
		players.addAll(wr.getPlayers());
		players.addAll(te.getPlayers());
		players.addAll(flex.getPlayers());
		players.addAll(k.getPlayers());
		players.addAll(dst.getPlayers());
		return players;
	}

	public PlayerList getQb() {
		return qb;
	}

	public void setQb(PlayerList qb) {
		this.qb = qb;
	}

	public PlayerList getRb() {
		return rb;
	}

	public void setRb(PlayerList rb) {
		this.rb = rb;
	}

	public PlayerList getWr() {
		return wr;
	}

	public void setWr(PlayerList wr) {
		this.wr = wr;
	}

	public PlayerList getTe() {
		return te;
	}

	public void setTe(PlayerList te) {
		this.te = te;
	}

	public PlayerList getFlex() {
		return flex;
	}

	public void setFlex(PlayerList flex) {
		this.flex = flex;
	}

	public PlayerList getK() {
		return k;
	}

	public void setK(PlayerList k) {
		this.k = k;
	}

	public PlayerList getDst() {
		return dst;
	}

	public void setDst(PlayerList dst) {
		this.dst = dst;
	}

}
