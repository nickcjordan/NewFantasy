package com.fantasy.dataaccessutility.model.to;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.Position;
import com.fantasy.dataaccessutility.model.team.PlayerList;

@DynamoDBDocument
public class StartingLineupTO {
	
	private PlayerListTO qb;
	private PlayerListTO rb;
	private PlayerListTO wr;
	private PlayerListTO te;
	private PlayerListTO flex;
	private PlayerListTO k;
	private PlayerListTO dst;

	public StartingLineupTO() {
		this.qb = new PlayerListTO();
		this.rb = new PlayerListTO();
		this.wr = new PlayerListTO();
		this.te = new PlayerListTO();
		this.flex = new PlayerListTO();
		this.k = new PlayerListTO();
		this.dst = new PlayerListTO();
	}
	
	public void addPlayerIdToPositionSlotTO(Player player) {
		getListFromPlayerPosition(player.getPlayerPosition()).addPlayerId(player.getPlayerId());
	}
	
	public void addPlayerIdToFlexSlotTO(Player player) {
		flex.addPlayerId(player.getPlayerId());
	}
	
	private PlayerListTO getListFromPlayerPosition(String position) {
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

	public PlayerListTO getQb() {
		return qb;
	}

	public void setQb(PlayerListTO qb) {
		this.qb = qb;
	}

	public PlayerListTO getRb() {
		return rb;
	}

	public void setRb(PlayerListTO rb) {
		this.rb = rb;
	}

	public PlayerListTO getWr() {
		return wr;
	}

	public void setWr(PlayerListTO wr) {
		this.wr = wr;
	}

	public PlayerListTO getTe() {
		return te;
	}

	public void setTe(PlayerListTO te) {
		this.te = te;
	}

	public PlayerListTO getFlex() {
		return flex;
	}

	public void setFlex(PlayerListTO flex) {
		this.flex = flex;
	}

	public PlayerListTO getK() {
		return k;
	}

	public void setK(PlayerListTO k) {
		this.k = k;
	}

	public PlayerListTO getDst() {
		return dst;
	}

	public void setDst(PlayerListTO dst) {
		this.dst = dst;
	}

}
