package com.fantasy.dataaccessutility.model.ui;

public class EditLineupRequest {
	
	private String playerId;
	private String player2Id;
	private String userId;
	private String action;
	
	public String getPlayerId() {
		return playerId;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	public String getPlayer2Id() {
		return player2Id;
	}
	public void setPlayer2Id(String player2Id) {
		this.player2Id = player2Id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}

}
