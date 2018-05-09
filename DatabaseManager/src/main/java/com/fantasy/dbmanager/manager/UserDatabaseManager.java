package com.fantasy.dbmanager.manager;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dbmanager.dao.UserDao;
import com.fantasy.dbmanager.dao.UserDao;
import com.fantasy.dbmanager.model.User;
import com.fantasy.dbmanager.model.User;
import com.mongodb.client.result.DeleteResult;

@Component
public class UserDatabaseManager {
	
	private static Logger log = Logger.getLogger(UserDatabaseManager.class);
	
	@Autowired
	private UserDao userDao;

	public void put(List<User> users) {
		userDao.put(users);
	}
	
	public long count() {
		return userDao.getUserCount();
	}
	
	public List<User> get() {
		List<User> users = new ArrayList<User>();
		for (User p : userDao.getAll()) {
			users.add(p);
		}
		return users;
	}

	public boolean clear() {
		return userDao.removeAll();
	}

}
