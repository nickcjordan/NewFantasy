package com.fantasy.dbmanager.processor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dataaccessutility.model.modifier.Modifier;
import com.fantasy.dataaccessutility.model.ui.ModifierUpdateRequest;
import com.fantasy.dbmanager.manager.MetadataDatabaseManager;
import com.fantasy.dbmanager.manager.ModifierDatabaseManager;
import com.fantasy.dbmanager.manager.PlayerDatabaseManager;
import com.fantasy.dbmanager.manager.UserDatabaseManager;

@Component
public class ModifierUpdateRequestProcessor {
	
	private static final Logger log = LoggerFactory.getLogger(ModifierUpdateRequestProcessor.class);
	
	@Autowired
	private UserDatabaseManager userManager;
	
	@Autowired
	private PlayerDatabaseManager playerManager;
	
	@Autowired
	private ModifierDatabaseManager modifierManager;
	
	@Autowired
	private MetadataDatabaseManager metadataManager;

	public void processRequest(ModifierUpdateRequest modifierUpdateRequest) {
		 User user = userManager.get(modifierUpdateRequest.getUserId());
		 Modifier modifier = modifierManager.get(modifierUpdateRequest.getModifierId());
		 if (user != null && modifier != null && modifierUpdateRequest.getAction() != null) {
			 switch(modifierUpdateRequest.getAction()) {
			 	case "BUY": purchaseModifierForUser(modifier, user); break;
			 	case "SELL": sellModifierForUser(modifier, user); break;
			 	case "APPLY": applyModifierToPlayer(modifier, modifierUpdateRequest.getPlayerId()); break;
			 	case "REMOVE": removeModifierFromPlayer(modifier, modifierUpdateRequest.getPlayerId()); break;
			 	default:  log.error("ERROR :: no modifier action found for: " + modifierUpdateRequest.getAction()); break;
			 }
			 log.info("Modifier update request processed successfully :: updating database with changes...");
			 modifierManager.update(modifier);
			 userManager.update(user);
			 log.info("SUCCESS :: Proccessed modifier update request and updated database with changes");
		 } else {
			 log.error("ERROR :: did not process modifier update request");
		 }
	}
	
	private void removeModifierFromPlayer(Modifier modifier, String playerId) {
		log.info("getting player with id=" + playerId);
		 Player player = playerManager.get(playerId);
		 log.info("Removing modifier " + modifier.getModifierName() + " from player " + player.getPlayerName());
		 player.setAppliedModifier(null);
		 modifier.setTargetPlayerId(null);
		 playerManager.updatePlayer(player);
	}

	private void applyModifierToPlayer(Modifier modifier, String playerId) {
		log.info("getting player with id=" + playerId);
		 Player player = playerManager.get(playerId);
		 log.info("Applying modifier " + modifier.getModifierName() + " to player " + player.getPlayerName());
		 modifier.setTargetPlayerId(playerId);
		 player.setAppliedModifier(modifier);
		 playerManager.updatePlayer(player);
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
		modifier.setTargetUserId(null);
		modifier.setTargetPlayerId(null);
		//update
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
		
		if (modifier.getChangePercentage() < 0) {
			modifier.setTargetUserId(user.getMatchupSchedule().getScheduleByWeek().get(metadataManager.get("currentWeekNumber").getValue()).getOpponentUserId());
		} else {
			modifier.setTargetUserId(user.getUserId());
		}
		
	}
	
	
	
	

}
