package com.fantasy.dbmanager.processor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.Position;
import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dataaccessutility.model.team.Roster;
import com.fantasy.dataaccessutility.model.ui.EditLineupAction;
import com.fantasy.dataaccessutility.model.ui.EditLineupQuery;
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
			if (StringUtils.isNumeric(action.getActionText())) {
				Player playerToSwapWith = playerManager.get(action.getActionText());
				user.getTeam().getRoster().swapBenchAndLineupPlayers(player, playerToSwapWith);
			} else {
				executeActionFromRequest(user.getTeam().getRoster(), player, action);
			}
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
			case DROP_PLAYER: dropPlayerFromTeam(player, roster);
				break;
			case ADD_PLAYER_TO_BENCH: addPlayerToBenchFromWaivers(player, roster);
				break;
			case SWAP: log.info("ERROR :: got SWAP from edit lineup request -- should not have happened");
				break;
		default:
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

	private void dropPlayerFromTeam(Player player, Roster roster) {
		log.info("Dropping player " + player.getPlayerName() + " from team");
		roster.dropPlayerFromRoster(player);
		playerManager.updatePlayer(player);
	}
	
	private void addPlayerToBenchFromWaivers(Player player, Roster roster) {
		log.info("Adding player " + player.getPlayerName() + " to the bench from waivers");
		roster.addPlayerToBench(player);
		playerManager.updatePlayer(player);
	}

	public List<Player> getLineupOptions(EditLineupQuery query) {
		List<Player> players = new ArrayList<Player>();
		User user = userManager.get(query.getUserId());
		Player player = playerManager.get(query.getPlayerId());
		switch (Position.get(player.getPosition())) {
			case QUARTERBACK: players.addAll(user.getTeam().getRoster().getStartingLineup().getQb().getPlayers()); break;
			case RUNNINGBACK: players.addAll(user.getTeam().getRoster().getStartingLineup().getRb().getPlayers()); 
				players.addAll(user.getTeam().getRoster().getStartingLineup().getFlex().getPlayers()); break;
			case WIDERECEIVER: players.addAll(user.getTeam().getRoster().getStartingLineup().getRb().getPlayers()); 
				players.addAll(user.getTeam().getRoster().getStartingLineup().getFlex().getPlayers()); break;
			case TIGHTEND: players.addAll(user.getTeam().getRoster().getStartingLineup().getRb().getPlayers()); 
				players.addAll(user.getTeam().getRoster().getStartingLineup().getFlex().getPlayers()); break;
			case KICKER: players.addAll(user.getTeam().getRoster().getStartingLineup().getRb().getPlayers()); break;
			case DEFENSE: players.addAll(user.getTeam().getRoster().getStartingLineup().getRb().getPlayers()); break;
		}
		log.info("Got " + players.size() + " players for swappable call for: " + player.getPlayerName());
		return players;
	}

}
