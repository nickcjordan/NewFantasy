package com.fantasy.dbmanager.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dataaccessutility.model.ui.EditLineupRequest;
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

	public void putAll(List<User> users) {
		List<UserTO> userTos = new ArrayList<UserTO>();
		for (User u : users) {
			userTos.add(userTransformer.getTO(u));
		}
		userDao.putAll(userTos);
	}
	
	public void put(User user) {
		userDao.put(userTransformer.getTO(user));
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
		if (userDao.update(userTransformer.getTO(user))) {
			log.info("SUCCESS :: User data updated in database :: " + user.getUserName());
		} else {
			log.info("ERROR :: User data could not be updated in database :: " + user.getUserName());
			throw new RuntimeException();
		}
	}
	
	public void updateAll(List<User> users) { // TODO call batch
		for (User user : users) {
			try {
				update(user);
			} catch (Exception e) {
				log.info("update did not work for " + user.getUserName() + ", trying put...");
				put(user);
			}
		}
	}

}
