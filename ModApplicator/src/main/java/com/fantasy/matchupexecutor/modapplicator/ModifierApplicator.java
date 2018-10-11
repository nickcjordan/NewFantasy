package com.fantasy.matchupexecutor.modapplicator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.ModifiedStats;
import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dataaccessutility.model.matchup.Matchup;
import com.fantasy.dataaccessutility.model.modifier.Modifier;
import com.fantasy.dataaccessutility.model.team.Team;

@Component
public class ModifierApplicator {
	
	@Autowired
	private TargetDeterminator determinator;
	
	private String weekNumber;
	
	//TODO 
	
//	public void applyModsToMatchup(Matchup matchup) {
//		weekNumber = matchup.getWeekNumber();
//		User userA = matchup.getUsers().get(0);
//		User userB = matchup.getUsers().get(1);
//		applyModifiersFromUser(userA, userB);
//		applyModifiersFromUser(userB, userA);
//	}
//
//	private void applyModifiersFromUser(User user, User opponent) {
//		for (Modifier mod : user.getModifiers()) {
//			switch(mod.getTargetType()) {
//				case PLAYER : 	  applyModifierOnPlayer(mod, determinator.determineTargetPlayer(mod.getTargetId(), user, opponent)); break;
//				case TEAM: 		  applyModifierOnTeam(mod, determinator.determineTargetTeam(mod.getTargetId(), user, opponent)); break;
//				case POSITION:	  applyModifierOnPositionGroup(mod, determinator.determineTargetPositionGroup(mod, user, opponent).getPlayers()); break;
//			}
//		}
//	}
//
//	private void applyModifierOnTeam(Modifier mod, Team team) {
//		for (Player player : team.getRoster().getAllPlayers()) {
//			player.getModifiedStats().get(weekNumber).setNewFantasyPointTotal(calculateNewFantasyPointTotalAddition(player, mod));
//		}
//	}
//
//	private double calculateNewFantasyPointTotalAddition(Player player, Modifier mod) {
//		double initialPointTotal = player.getStatsByWeek().get(weekNumber).getTotalPointsScored();
//		double modified = (initialPointTotal * ((mod.getChangePercentage())/100.00));
//		return player.getModifiedStats().get(weekNumber).getNewFantasyPointTotal() + modified;
//	}
//
//	private void applyModifierOnPositionGroup(Modifier mod, List<Player> players) {
//		for (Player player : players) {
//			player.getModifiedStats().get(weekNumber).setNewFantasyPointTotal(calculateNewFantasyPointTotalAddition(player, mod));
//		}
//	}
//
//	private void applyModifierOnPlayer(Modifier mod, Player player) {
//		player.getModifiedStats().get(weekNumber).setNewFantasyPointTotal(calculateNewFantasyPointTotalAddition(player, mod));
//	}
//	
}
