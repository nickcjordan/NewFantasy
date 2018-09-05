package com.fantasy.dbmanager.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dataaccessutility.model.team.Roster;
import com.fantasy.dataaccessutility.model.ui.EditLineupAction;
import com.fantasy.dataaccessutility.model.ui.EditLineupRequest;
import com.fantasy.dbmanager.manager.PlayerDatabaseManager;
import com.fantasy.dbmanager.manager.UserDatabaseManager;

@Component
public class EditLineupRequestProcessor {
	
	private static final Logger log = LoggerFactory.getLogger(EditLineupRequestProcessor.class);

	@Autowired
	private UserDatabaseManager userManager;
	
	@Autowired
	private PlayerDatabaseManager playerManager;
	

	public void editLineup(EditLineupRequest request) {
		User user = userManager.get(request.getUserId());
		Player player = playerManager.get(request.getPlayerId());
		EditLineupAction action = EditLineupAction.getActionFromText(request.getAction());
		if (user != null && player != null && action != null) {
			executeActionFromRequest(user.getTeam().getRoster(), player, action);
		} else {
			log.error("EditLineupRequestProcessor :: ERROR trying to edit lineup :: could not find one or more elements from request :: user: " + request.getUserId() + ", player: " + request.getPlayerId() + ", action: " + request.getAction());
		}
		log.info("Edit lineup request executed :: saving team to database...");
		userManager.update(user);
	}

	private void executeActionFromRequest(Roster roster, Player player, EditLineupAction action) {
		switch(action) {
			case START_PLAYER_AT_POSITION: movePlayerFromBenchToLineupAtPosition(player, roster);
				break;
			case START_PLAYER_AT_FLEX: movePlayerFromBenchToLineupAtFlex(player, roster);
				break;
			case MOVE_PLAYER_TO_BENCH: movePlayerFromLineupToBench(player, roster);
				break;
			case DROP_PLAYER: dropPlayer(player, roster);
				break;
		}
	}
	
	private void movePlayerFromBenchToLineupAtPosition(Player player, Roster roster) {
		log.info("Adding player " + player.getPlayerName() + " to lineup at position: " + player.getPosition() + "  :: and removing from bench");
		roster.removePlayerFromBench(player);
		roster.addPlayerToLineupAndMoveReplacedPlayerToBench(player);
	}
	
	private void movePlayerFromBenchToLineupAtFlex(Player player, Roster roster) {
		log.info("Adding player " + player.getPlayerName() + " to lineup as flex :: removing from bench");
		roster.removePlayerFromBench(player);
		roster.addFlexPlayerToLineupAndMoveReplacedPlayerToBench(player);
	}

	private void movePlayerFromLineupToBench(Player player, Roster roster) {
		log.info("Removing player " + player.getPlayerName() + " from lineup :: adding to bench");
		roster.removePlayerFromLineup(player);
		roster.addPlayerToBench(player);
	}

	private void dropPlayer(Player player, Roster roster) {
		log.info("Dropping player " + player.getPlayerName() + " from team");
		roster.dropPlayerFromRoster(player);
	}

}
