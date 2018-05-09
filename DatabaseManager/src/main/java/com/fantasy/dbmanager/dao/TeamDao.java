package com.fantasy.dbmanager.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fantasy.dbmanager.controller.TeamDatabaseController;
import com.fantasy.dbmanager.model.Team;
import com.fantasy.dbmanager.model.Team;
import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;

import static com.mongodb.client.model.Filters.*;

@Component
public class TeamDao {
	
	@Autowired
	@Qualifier("teamDBCollection")
	MongoCollection<Team> teamDBCollection;
	
	public void put(List<Team> teams) {
		teamDBCollection.insertMany(teams);
	}

	public long getTeamCount() {
		return teamDBCollection.count();
	}
	
	public FindIterable<Team> getAll() {
		return teamDBCollection.find();
	}

//	public boolean remove(Team team) {
//		Bson filter = eq("id", team.getId());
//		DeleteResult results = teamDBCollection.deleteOne(filter);
//		return results.getDeletedCount() > 0;
//	}

	public boolean removeAll() {
		teamDBCollection.deleteMany(eq("id", null));
		return true;
	}

}
