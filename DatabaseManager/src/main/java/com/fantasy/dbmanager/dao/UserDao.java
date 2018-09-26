package com.fantasy.dbmanager.dao;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fantasy.dbmanager.model.UserTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;

@Component
public class UserDao {
	
	@Autowired
	@Qualifier("userDBCollection")
	MongoCollection<UserTO> userDBCollection;
	
	public void putAll(List<UserTO> users) {
		userDBCollection.insertMany(users);
	}

	public void put(UserTO to) {
		userDBCollection.insertOne(to);
	}

	public long getUserCount() {
		return userDBCollection.count();
	}
	
	public FindIterable<UserTO> getAll() {
		return userDBCollection.find();
	}

//	public boolean remove(User user) {
//		Bson filter = eq("id", user.getId());
//		DeleteResult results = userDBCollection.deleteOne(filter);
//		return results.getDeletedCount() > 0;
//	}

	public boolean removeAll() {
		userDBCollection.deleteMany(eq("id", null));
		return true;
	}

	public UserTO getUserById(String id) {
		try {
			return userDBCollection.find(eq("userId", id)).first();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public boolean update(UserTO userTO) {
		try {
			return (userDBCollection.updateOne(eq("userId", userTO.getUserId()), new Document("$set", Document.parse(new ObjectMapper().writeValueAsString(userTO)))).getModifiedCount() > 0);
		} catch (Exception e) {
			e.printStackTrace(); // TODO
			return false;
		}
	}


}
