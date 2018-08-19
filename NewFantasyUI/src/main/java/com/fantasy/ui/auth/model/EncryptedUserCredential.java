package com.fantasy.ui.auth.model;

import com.fantasy.ui.auth.AES;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class EncryptedUserCredential extends UserCredential {
	
	private AES aes;
	
	public EncryptedUserCredential() {
		super();
		this.aes = new AES();
	}

	public EncryptedUserCredential(String id, String firstName, String lastName, String username, String plainTextPassword, String token) {
		super(id, firstName, lastName, username, plainTextPassword, token);
		this.aes = new AES();
		super.setPassword(aes.encrypt(plainTextPassword));
	}

	public EncryptedUserCredential(String username, String plainTextPassword) {
		super(username, plainTextPassword);
		this.aes = new AES();
		super.setPassword(aes.encrypt(plainTextPassword));
	}

	public EncryptedUserCredential(UserCredential user) {
		this(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.getToken());
	}
	
	@JsonIgnore
	public String getDecryptedPassword() {
		return aes.decrypt(super.getPassword());
	}

}
