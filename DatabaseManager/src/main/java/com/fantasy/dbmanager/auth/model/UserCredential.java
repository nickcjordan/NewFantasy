package com.fantasy.dbmanager.auth.model;

import com.fantasy.dbmanager.auth.UserCredentialDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using=UserCredentialDeserializer.class)
public class UserCredential {
	
	private String id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String token;
	
	public UserCredential() {}
	
	public UserCredential(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public UserCredential(String id, String firstName, String lastName, String username, String password, String token) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.token = token;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
