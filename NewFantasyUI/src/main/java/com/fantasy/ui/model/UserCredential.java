package com.fantasy.ui.model;

import java.security.Principal;

public class UserCredential implements Principal {
	
	private String name;
	private String username;
	private String password;
	
	
	@Override
	public String getName() {
		return name;
	}
	
	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

}
