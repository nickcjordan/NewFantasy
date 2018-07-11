package com.fantasy.dbmanager.dao;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fantasy.dbmanager.model.TeamTO;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

@Component
public class TeamDao {
	
	@Autowired
	@Qualifier("teamDBCollection")
	MongoCollection<TeamTO> teamDBCollection;
	
	public void put(List<TeamTO> teams) {
		teamDBCollection.insertMany(teams);
	}

	public long getTeamCount() {
		return teamDBCollection.count();
	}
	
	public FindIterable<TeamTO> getAll() {
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
