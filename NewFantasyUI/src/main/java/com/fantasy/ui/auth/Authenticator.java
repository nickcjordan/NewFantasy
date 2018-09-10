package com.fantasy.ui.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fantasy.ui.auth.model.EncryptedUserCredential;
import com.fantasy.ui.auth.model.UserCredential;

@Component
public class Authenticator {
	
	private Map<String, EncryptedUserCredential> credentials;
	
	public Authenticator() {
		 credentials = new HashMap<String, EncryptedUserCredential>();
		 credentials.put("njo", new EncryptedUserCredential("4", "nick", "jordan", "njo", "njopass", "fake-jwt-token"));
		 credentials.put("dmo", new EncryptedUserCredential("11", "dan", "moomau", "dmo", "dmopass", "fake-jwt-token"));
		 credentials.put("rwi", new EncryptedUserCredential("5", "ryan", "williams", "rwi", "rwipass", "fake-jwt-token"));
		 credentials.put("wre", new EncryptedUserCredential("2", "will", "reeves", "wre", "wrepass", "fake-jwt-token"));
	}
	
	public boolean credentialsAreAuthorized(String username, String password) {
		EncryptedUserCredential cred = credentials.get(username);
		return (cred != null && (cred.getDecryptedPassword().equals(password)));
	}
	
	public boolean isAuthorizedUser(UserCredential user) {
		return credentialsAreAuthorized(user.getUsername(), user.getPassword());
	}

	public List<UserCredential> getRegisteredUsers() {
		return new ArrayList<UserCredential>(credentials.values());
	}

	public UserCredential getRegisteredUser(String username) {
		return credentials.get(username);
	}

	public boolean registerNewUser(UserCredential user) {
		return ((credentials.putIfAbsent(user.getUsername(), new EncryptedUserCredential(user))) == null);
	}

	public boolean deleteUser(String username) {
		return (credentials.remove(username) != null);
	}

}
