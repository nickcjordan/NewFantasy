package com.fantasy.dbmanager.dao;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fantasy.dbmanager.model.User;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

@Component
public class UserDao {
	
	@Autowired
	@Qualifier("userDBCollection")
	MongoCollection<User> userDBCollection;
	
	public void put(List<User> users) {
		userDBCollection.insertMany(users);
	}

	public long getUserCount() {
		return userDBCollection.count();
	}
	
	public FindIterable<User> getAll() {
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

}
