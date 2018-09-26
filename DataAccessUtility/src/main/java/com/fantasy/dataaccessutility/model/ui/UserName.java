package com.fantasy.dataaccessutility.model.ui;

public class UserName {
	
	private String userId;
	private String userName;
	
	public UserName() {}
	
	public UserName(String userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
