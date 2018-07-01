package com.fantasy.matchupexecutor.initializer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fantasy.matchupexecutor.model.NFLPlayerWeeklyStats;
import com.fantasy.matchupexecutor.model.NewFantasyWeeklyStats;
import com.fantasy.matchupexecutor.model.Player;
import com.fantasy.matchupexecutor.model.User;
import com.fantasy.matchupexecutor.model.matchup.Matchup;
import com.fantasy.matchupexecutor.model.modifier.Modifier;

@Component
public class MatchupInitializer {
	
	@Autowired
	private DatabaseManagerDelegate updater;

	public void populateMatchupWithInitialStats(Matchup matchup) {
		//  grab stats from nfl and populate players with those stats
		for (User user : matchup.getUsers()) {
			for (Player player : user.getTeam().getListOfPlayers()) {
				Player newPlayer = updater.getUpdatedPlayer(player); //TODO this would work, right? because its pass by reference?
				player.setWeeklyStats(newPlayer.getWeeklyStats());
				buildAndSetNewFantasyStats(Integer.toString(matchup.getWeekNumber()), player);
			}
		}
	}
	
	private void buildAndSetNewFantasyStats(String weekNumber, Player player) {
		NFLPlayerWeeklyStats nflStats = player.getWeeklyStats().get(weekNumber);
		double initialPointTotal = nflStats.getWeeklyPoints();
		player.getNewFantasyWeeklyStats().put(weekNumber, new NewFantasyWeeklyStats(weekNumber, initialPointTotal, initialPointTotal));
	}
	
}
