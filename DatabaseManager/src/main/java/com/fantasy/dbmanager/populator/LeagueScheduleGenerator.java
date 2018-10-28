package com.fantasy.dbmanager.populator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dataaccessutility.model.matchup.LeagueMatchupSchedule;
import com.fantasy.dataaccessutility.model.matchup.Matchup;
import com.fantasy.dataaccessutility.model.matchup.MatchupTO;
import com.fantasy.dataaccessutility.model.to.UserTO;
import com.fantasy.dbmanager.controller.UserDatabaseController;

@Component
public class LeagueScheduleGenerator {
	
	@Autowired
	private UserDatabaseController userData;
	
	@Value("${matchup.schedule.length}")
	private String matchupScheduleLength;
	
	private List<UserTO> colA;
	private List<UserTO> colB;
	
	// 	TODO  need to add algorithm for generating schedule with odd number of teams and a bye
	
	public LeagueMatchupSchedule generateLeagueSchedule() {
		LeagueMatchupSchedule schedule = buildLeagueSchedule();
		for (Entry<String, List<MatchupTO>> entry : schedule.getMatchupTOListsByWeek().entrySet()) {
			for (MatchupTO matchup : entry.getValue()) {
				populateUsersWithMatchup(matchup);
			}
		}
		return schedule;
	}

	private void populateUsersWithMatchup(MatchupTO matchup) {
		populateUser(userData.get(matchup.getUserIdA()), new Matchup(matchup.getWeekNumber(), matchup.getUserIdA(), matchup.getUserIdB()));
		populateUser(userData.get(matchup.getUserIdB()), new Matchup(matchup.getWeekNumber(), matchup.getUserIdB(), matchup.getUserIdA()));
	}

	private void populateUser(User user, Matchup m) {
		user.getMatchupSchedule().setUserId(user.getUserId());
		user.getMatchupSchedule().getScheduleByWeek().put(m.getWeekNumber(), m);
		userData.updateUser(user);
	}

	private LeagueMatchupSchedule buildLeagueSchedule() {
		LeagueMatchupSchedule schedule = new LeagueMatchupSchedule(Integer.valueOf(matchupScheduleLength));
		List<UserTO> rotate = userData.getAllTO();
        int week = 1;
        int middle = ( (rotate.size() / 2) );

        while (schedule.lastWeekOfSeasonIsEmpty()) {
            colA = new ArrayList<UserTO>(rotate.subList(0, middle));
            colB = new ArrayList<UserTO>(rotate.subList(middle, rotate.size()));
            Collections.reverse(colB);
           
            for (int i = 0; i < colA.size(); i++) {
                schedule.addMatchup(String.valueOf(week), colA.get(i).getUserId(), colB.get(i).getUserId());
            }
            week++;
            rotate.add(1, rotate.remove(rotate.size()-1));
        } 
		
		return schedule;
	}
	
	
	
		
}


