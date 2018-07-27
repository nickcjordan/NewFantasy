package com.fantasy.dbmanager.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dbmanager.dao.UserDao;
import com.fantasy.dbmanager.model.UserTO;
import com.fantasy.dbmanager.transformer.UserTransformer;

@Component
public class UserDatabaseManager {
	
	private static Logger log = Logger.getLogger(UserDatabaseManager.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserTransformer userTransformer;

	public void put(List<User> users) {
		List<UserTO> userTos = new ArrayList<UserTO>();
		for (User u : users) {
			userTos.add(userTransformer.getTO(u));
		}
		userDao.put(userTos);
	}
	
	public long count() {
		return userDao.getUserCount();
	}
	
	public List<User> get() {
		List<User> users = new ArrayList<User>();
		for (UserTO userTo : userDao.getAll()) {
			users.add(userTransformer.getUser(userTo));
		}
		return users;
	}

	public boolean clear() {
		return userDao.removeAll();
	}

	public User get(String id) {
		return userTransformer.getUser(userDao.getUserById(id));
	}

	public void update(User user) {
		
	}

}
