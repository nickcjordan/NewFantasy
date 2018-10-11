package com.fantasy.dbmanager.dao;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.modifier.Modifier;
import com.fantasy.dataaccessutility.model.to.TeamTO;
import com.fantasy.dataaccessutility.model.to.UserTO;
import com.fasterxml.jackson.databind.ObjectMapper;
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

	public Modifier get(String modifierId) {
		return modifierDBCollection.find(eq("modifierId", modifierId)).first();
	}

	public boolean update(Modifier modifier) {
		try {
			return (modifierDBCollection.updateOne(eq("modifierId", modifier.getModifierId()), new Document("$set", Document.parse(new ObjectMapper().writeValueAsString(modifier)))).getModifiedCount() > 0);
		} catch (Exception e) {
			e.printStackTrace(); // TODO
			return false;
		}
	}

}
