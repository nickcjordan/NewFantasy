package com.fantasy.dataaccessutility.model;

import java.util.HashMap;
import java.util.Map;

import com.fantasy.dataaccessutility.model.modifier.Modifier;

public class Player {
	
	private String playerId;
	private String position;
	private boolean isOnUserTeam;
	private String playerName;
	private String teamName;
	private String playerRank;
	private String positionRank;
	private String byeWeek;
	private String nflTeamId;
	private String imageUrl;
	private String smallImageUrl;
	private Modifier appliedModifier;
	private Map<String, PositionStatsDetails> statsByWeek;
	private Map<String, ModifiedStats> modifiedStats;
	
	public Player() {
		this.statsByWeek = new HashMap<String, PositionStatsDetails>();
		this.modifiedStats = new HashMap<String, ModifiedStats>();
	}

	public Player(String id) {
		this.statsByWeek = new HashMap<String, PositionStatsDetails>();
		this.modifiedStats = new HashMap<String, ModifiedStats>();
		this.playerId = id;
	}
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getPlayerRank() {
		return playerRank;
	}

	public void setPlayerRank(String playerRank) {
		this.playerRank = playerRank;
	}

	public String getPositionRank() {
		return positionRank;
	}

	public void setPositionRank(String positionRank) {
		this.positionRank = positionRank;
	}

	public String getByeWeek() {
		return byeWeek;
	}

	public void setByeWeek(String byeWeek) {
		this.byeWeek = byeWeek;
	}

	public boolean isOnUserTeam() {
		return isOnUserTeam;
	}

	public void setOnUserTeam(boolean isOnUserTeam) {
		this.isOnUserTeam = isOnUserTeam;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Map<String, PositionStatsDetails> getStatsByWeek() {
		return statsByWeek;
	}

	public void setStatsByWeek(Map<String, PositionStatsDetails> statsByWeek) {
		this.statsByWeek = statsByWeek;
	}

	public String getNflTeamId() {
		return nflTeamId;
	}

	public void setNflTeamId(String nflTeamId) {
		this.nflTeamId = nflTeamId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSmallImageUrl() {
		return smallImageUrl;
	}

	public void setSmallImageUrl(String smallImageUrl) {
		this.smallImageUrl = smallImageUrl;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public Map<String, ModifiedStats> getModifiedStats() {
		return modifiedStats;
	}

	public void setModifiedStats(Map<String, ModifiedStats> modifiedStats) {
		this.modifiedStats = modifiedStats;
	}

	public Modifier getAppliedModifier() {
		return appliedModifier;
	}

	public void setAppliedModifier(Modifier appliedModifier) {
		this.appliedModifier = appliedModifier;
	}
	
}
