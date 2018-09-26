package com.fantasy.dbmanager.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dataaccessutility.model.modifier.Modifier;
import com.fantasy.dbmanager.dao.ModifierDao;
import com.fantasy.dbmanager.model.UserTO;

@Component
public class UserTransformer {
	
	@Autowired
	private TeamTransformer teamTransformer;
	
	@Autowired
	private ModifierDao modifierDao;

	public UserTO getTO(User user) {
		UserTO userTo = new UserTO();
		userTo.setCoins(user.getCoins());
		userTo.setUserId(user.getUserId());
		userTo.setMatchupResults(user.getMatchupResults());
		userTo.setModifiers(buildModiferIdList(user.getModifiers()));
		userTo.setPerkTree(user.getPerkTree());
		userTo.setTeam(teamTransformer.getTO(user.getTeam()));
		userTo.setUserName(user.getUserName());
		return userTo;
	}

	private List<String> buildModiferIdList(List<Modifier> modifiers) {
		List<String> modifierIdList = new ArrayList<String>();
		for (Modifier m : modifiers) {
			modifierIdList.add(m.getModifierId());
		}
		return modifierIdList;
	}

	public User getUser(UserTO userTo) {
		User user = new User();
		user.setCoins(userTo.getCoins());
		user.setUserId(userTo.getUserId());
		user.setMatchupResults(userTo.getMatchupResults());
		user.setModifiers(buildModifiers(userTo.getModifiers()));
		user.setPerkTree(userTo.getPerkTree());
		user.setTeam(teamTransformer.getTeam(userTo.getTeam()));
		user.setUserName(userTo.getUserName());
		return user;
	}

	private List<Modifier> buildModifiers(List<String> modifierIds) {
		List<Modifier> modifiers = new ArrayList<Modifier>();
		for (String id : modifierIds) {
			modifiers.add(modifierDao.get(id));
		}
		return modifiers;
	}
	
}
