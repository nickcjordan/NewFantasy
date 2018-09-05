package com.fantasy.matchupexecutor.results;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.ModifiedStats;
import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dataaccessutility.model.matchup.Matchup;
import com.fantasy.dataaccessutility.model.matchup.MatchupResults;
import com.fantasy.dataaccessutility.model.matchup.MatchupUserResult;

@Component
public class MatchupResultsProcessor {
	
	private static Logger log = Logger.getLogger(MatchupResultsProcessor.class);

	public MatchupResults processMatchupResults(Matchup matchup) {
		MatchupResults results = new MatchupResults(Integer.valueOf(matchup.getWeekNumber()));
		User u1 = matchup.getUsers().get(0);
		User u2 = matchup.getUsers().get(1);
		MatchupUserResult user1Result = buildMatchupUserResult(matchup.getWeekNumber(), u1, u2);
		MatchupUserResult user2Result = buildMatchupUserResult(matchup.getWeekNumber(), u2, u1);
		processWinnerAndSetResults(results, u1, user1Result, u2, user2Result);
		return results;
	}

	private MatchupUserResult buildMatchupUserResult(String week, User user, User opponent) {
		MatchupUserResult result = new MatchupUserResult(Integer.valueOf(week));
		result.setOpponentId(opponent.getUserId());
		result.setWeek(Integer.valueOf(week));
		result.setModifiersApplied(user.getModifiers());
		result.setTotalPointsScored(calculateTotalPointsScored(Integer.valueOf(week), user));
		result.setCoinsEarned((int)Math.floor(result.getTotalPointsScored()));
		result.setMatchupUserResultId(user.getUserId() + week);
		user.getMatchupResults().put(week, result);
		user.addCoins(result.getCoinsEarned());
		return result;
	}

	private double calculateTotalPointsScored(int week, User user) {
		double sum = 0;
		for (Player player : user.getTeam().getRoster().getAllPlayers()) {
			try {
				Map<String, ModifiedStats> stats = player.getModifiedStats();
				ModifiedStats stat = stats.get(Integer.toString(week));
				sum += stat.getNewFantasyPointTotal();
			} catch(Exception e) {
				log.error("ERROR :: week=" + week + " :: getting " + player);
				e.printStackTrace();
			}
		}
		return sum;
	}

	private void processWinnerAndSetResults(MatchupResults results, User u1, MatchupUserResult user1Result, User u2, MatchupUserResult user2Result) {
		if (user1Result.getTotalPointsScored() > user2Result.getTotalPointsScored()) {
			setWinnerAndLoser(results, u1, user1Result, u2, user2Result);
		} else if (user1Result.getTotalPointsScored() < user2Result.getTotalPointsScored()) {
			setWinnerAndLoser(results, u2, user2Result, u1, user1Result);
		} else {
			log.error("ERROR totalPointsScored is the same: " + user1Result.getTotalPointsScored() + " == " + user2Result.getTotalPointsScored());
		}
	}

	private void setWinnerAndLoser(MatchupResults results, User winner, MatchupUserResult winningTeamResults, User loser, MatchupUserResult losingTeamResults) {
		winningTeamResults.setWinner(true);
		losingTeamResults.setWinner(false);
		results.setLoser(loser);
		results.setWinner(winner);
		results.setLosingTeamResults(losingTeamResults);
		results.setWinningTeamResults(winningTeamResults);
	}


}
