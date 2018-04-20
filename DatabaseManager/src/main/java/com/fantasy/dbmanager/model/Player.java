package com.fantasy.dbmanager.model;

public class Player {
	
	int id;
	Position position;
	String playerName;
	String teamName;
	String playerRank;
	String positionRank;
	String byeWeek;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
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

}
