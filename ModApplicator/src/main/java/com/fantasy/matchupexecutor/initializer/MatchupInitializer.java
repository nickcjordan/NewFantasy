package com.fantasy.matchupexecutor.initializer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.DataAccessUtility;
import com.fantasy.dataaccessutility.model.ModifiedStats;
import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dataaccessutility.model.matchup.Matchup;
import com.fantasy.matchupexecutor.controller.MatchupExecutorController;
import com.fantasy.matchupexecutor.model.MatchupRequest;

@Component
public class MatchupInitializer {
	
	private static Logger log = Logger.getLogger(MatchupExecutorController.class);
	
	@Autowired
	private DataAccessUtility data;

	public Matchup initializeMatchup(MatchupRequest request) {
		Matchup matchup =  new Matchup(request.getWeekNumber());
		String weekNumber = request.getWeekNumber();
		
		for (String userId : request.getUserIds()) {
			User user = data.getUserById(userId);
			matchup.addUser(user);
			for (Player player : user.getTeam().getRoster().getAllPlayers()) {
				double initialPointTotal = 0;
				try {
					initialPointTotal = player.getStatsByWeek().get(weekNumber).getTotalPointsScored();
				} catch (Exception e) {
					log.error("ERROR getting initialPointTotal for player " + player);
				}
				player.getModifiedStats().put(weekNumber,  new ModifiedStats(weekNumber, initialPointTotal)); // setting end to start, getting updated later
			}
		}
		return matchup;
	}
	
}
