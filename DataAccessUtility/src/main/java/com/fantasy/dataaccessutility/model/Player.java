package com.fantasy.dataaccessutility.model;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fantasy.dataaccessutility.model.modifier.Modifier;

@DynamoDBTable(tableName = "player-table")
public class Player {
	
	private String playerId;
	private String playerPosition;
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
	@DynamoDBHashKey(attributeName = "playerId")
	public String getPlayerId() {
		return playerId;
	}
	@DynamoDBAttribute
	public String getPlayerName() {
		return playerName;
	}
	@DynamoDBAttribute(attributeName="playerPosition")
	public String getPlayerPosition() {
		return playerPosition;
	}
	@DynamoDBAttribute
	public boolean isOnUserTeam() {
		return isOnUserTeam;
	}
	@DynamoDBAttribute
	public String getTeamName() {
		return teamName;
	}
	@DynamoDBAttribute
	public String getPlayerRank() {
		return playerRank;
	}
	@DynamoDBAttribute
	public String getPositionRank() {
		return positionRank;
	}
	@DynamoDBAttribute
	public String getByeWeek() {
		return byeWeek;
	}
	@DynamoDBAttribute
	public String getNflTeamId() {
		return nflTeamId;
	}
	@DynamoDBAttribute
	public String getImageUrl() {
		return imageUrl;
	}
	@DynamoDBAttribute
	public String getSmallImageUrl() {
		return smallImageUrl;
	}
	@DynamoDBAttribute
	public Modifier getAppliedModifier() {
		return appliedModifier;
	}
	@DynamoDBAttribute
	public Map<String, PositionStatsDetails> getStatsByWeek() {
		return statsByWeek;
	}
	@DynamoDBAttribute
	public Map<String, ModifiedStats> getModifiedStats() {
		return modifiedStats;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public void setPlayerPosition(String position) {
		this.playerPosition = position;
	}

	public void setOnUserTeam(boolean isOnUserTeam) {
		this.isOnUserTeam = isOnUserTeam;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public void setPlayerRank(String playerRank) {
		this.playerRank = playerRank;
	}

	public void setPositionRank(String positionRank) {
		this.positionRank = positionRank;
	}

	public void setByeWeek(String byeWeek) {
		this.byeWeek = byeWeek;
	}

	public void setNflTeamId(String nflTeamId) {
		this.nflTeamId = nflTeamId;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setSmallImageUrl(String smallImageUrl) {
		this.smallImageUrl = smallImageUrl;
	}

	public void setAppliedModifier(Modifier appliedModifier) {
		this.appliedModifier = appliedModifier;
	}

	public void setStatsByWeek(Map<String, PositionStatsDetails> statsByWeek) {
		this.statsByWeek = statsByWeek;
	}

	public void setModifiedStats(Map<String, ModifiedStats> modifiedStats) {
		this.modifiedStats = modifiedStats;
	}
	
	
}
