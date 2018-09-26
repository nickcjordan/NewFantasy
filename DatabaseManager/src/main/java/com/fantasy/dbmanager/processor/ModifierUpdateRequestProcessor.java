package com.fantasy.dbmanager.processor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dataaccessutility.model.modifier.Modifier;
import com.fantasy.dataaccessutility.model.ui.ModifierUpdateRequest;
import com.fantasy.dbmanager.manager.ModifierDatabaseManager;
import com.fantasy.dbmanager.manager.UserDatabaseManager;

@Component
public class ModifierUpdateRequestProcessor {
	
	
	private static final Logger log = LoggerFactory.getLogger(ModifierUpdateRequestProcessor.class);

	
	@Autowired
	private UserDatabaseManager userManager;
	
	@Autowired
	private ModifierDatabaseManager modifierManager;

	public void processRequest(ModifierUpdateRequest modifierUpdateRequest) {
		 User user = userManager.get(modifierUpdateRequest.getUserId());
		 Modifier modifier = modifierManager.get(modifierUpdateRequest.getModifierId());
		 if (user != null && modifier != null && modifierUpdateRequest.getAction() != null) {
			 switch(modifierUpdateRequest.getAction()) {
			 	case "BUY": purchaseModifierForUser(modifier, user); break;
			 	case "SELL": sellModifierForUser(modifier, user); break;
			 	default:  log.error("ERROR :: no modifier action found for: " + modifierUpdateRequest.getAction()); break;
			 }
		 }
		 log.info("Modifier update request processed successfully :: updating database with changes...");
		 modifierManager.update(modifier);
		 userManager.update(user);
		 log.info("SUCCESS :: Proccessed modifier update request and updated database with changes");
	}

	private void purchaseModifierForUser(Modifier modifier, User user) {
		log.info("Purchasing modifier [" + modifier.getModifierName() + "] for user: " + user.getUserName());
		modifier.setOwningUserId(user.getUserId());
		
		boolean matched = false;
		for (Modifier m : user.getModifiers()) {
			 if (m.getModifierId().equals(modifier.getModifierId())) { matched = true; }
		}
		if (!matched) { user.getModifiers().add(modifier); }
		else { log.error("ERROR :: did not add modifier :: User " + user.getUserId() + " already owns modifier " + modifier.getModifierId()); }
		
		user.setCoins(user.getCoins() - modifier.getPrice());
		switch(modifier.getTargetType()) {
			case PLAYER: updateModifierTargetForPlayer(modifier, user); break;
			case POSITION: updateModifierTargetForPosition(modifier, user); break;
			case TEAM: updateModifierTargetForTeam(modifier, user); break;
		}
	}
	
	private void updateModifierTargetForTeam(Modifier modifier, User user) {
		 log.info("Making updates for TEAM target :: user=" + user.getUserId());
		 
		 //TODO
		 
		 log.info("TODO this part is not complete");
	}

	private void updateModifierTargetForPosition(Modifier modifier, User user) {
		log.info("Making updates for TEAM target :: user=" + user.getUserId());
		
		 // TODO
		
		 log.info("TODO this part is not complete");
	}

	private void updateModifierTargetForPlayer(Modifier modifier, User user) {
		log.info("Making updates for TEAM target :: user=" + user.getUserId());
		
		 // TODO
		
		 log.info("TODO this part is not complete");
	}

	private void sellModifierForUser(Modifier modifier, User user) {
		log.info("Selling modifier [" + modifier.getModifierName() + "] for user: " + user.getUserName());
		List<Modifier> filtered = new ArrayList<Modifier>();
		for (Modifier m : user.getModifiers()) {
			if (!m.getModifierId().equals(modifier.getModifierId())) {
				filtered.add(m);
			}
		}
		user.setModifiers(filtered);
		modifier.setOwningUserId(null);
		user.setCoins(user.getCoins() + modifier.getPrice());
		modifier.setTargetPosition(null);
		modifier.setTargetId(null);
		//update
	}
	
	

}
