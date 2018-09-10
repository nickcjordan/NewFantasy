package com.fantasy.dbmanager.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dbmanager.model.UserTO;

@Component
public class UserTransformer {
	
	@Autowired
	private TeamTransformer teamTransformer;

	public UserTO getTO(User user) {
		UserTO userTo = new UserTO();
		userTo.setCoins(user.getCoins());
		userTo.setUserId(user.getUserId());
		userTo.setMatchupResults(user.getMatchupResults());
		userTo.setModifiers(user.getModifiers());
		userTo.setPerkTree(user.getPerkTree());
		userTo.setTeam(teamTransformer.getTO(user.getTeam()));
		userTo.setUserName(user.getUserName());
		return userTo;
	}

	public User getUser(UserTO userTo) {
		User user = new User();
		user.setCoins(userTo.getCoins());
		user.setUserId(userTo.getUserId());
		user.setMatchupResults(userTo.getMatchupResults());
		user.setModifiers(userTo.getModifiers());
		user.setPerkTree(userTo.getPerkTree());
		user.setTeam(teamTransformer.getTeam(userTo.getTeam()));
		user.setUserName(userTo.getUserName());
		return user;
	}
	
}
