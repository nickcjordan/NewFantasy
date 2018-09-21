package com.fantasy.dbmanager.dao;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.modifier.Modifier;
import com.fantasy.dbmanager.model.TeamTO;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;

@Component
public class ModifierDao {
	
	@Autowired
	@Qualifier("modifierDBCollection")
	MongoCollection<Modifier> modifierDBCollection;
	
	public void put(List<Modifier> modifiers) {
		modifierDBCollection.insertMany(modifiers);
	}

	public long getTeamCount() {
		return modifierDBCollection.count();
	}
	
	public FindIterable<Modifier> getAll() {
		return modifierDBCollection.find();
	}

	public boolean removeAll() {
		return modifierDBCollection.deleteMany(eq("id", null)).wasAcknowledged();
	}

	public long getModifierCount() {
		return modifierDBCollection.count();
	}

}
