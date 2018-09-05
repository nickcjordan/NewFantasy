package com.fantasy.dataaccessutility.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.DataAccessUtility;
import com.fantasy.dataaccessutility.access.DatabaseManagerDelegate;
import com.fantasy.dataaccessutility.model.User;

@Component
public class UserDataAccessManager {
	
	private static Logger log = Logger.getLogger(UserDataAccessManager.class);
	
	@Autowired
	private DatabaseManagerDelegate delegate;
	
	public User getUserById(String userId) {
		log.info("Getting updated stats for user " + userId);
		return delegate.getUpdatedUser(userId);
	}
	public User getUserFromUser(User user) {
		return getUserById(user.getUserId());
	}
	public List<User> getUsersByIdList(List<String> userIds) {
		List<User> users = new ArrayList<User>();
		for (String id : userIds) {
			users.add(getUserById(id));
		}
		return users;
	}
	public List<User> getUsersFromUserList(List<User> usersRequest) {
		List<User> users = new ArrayList<User>();
		for (User p : usersRequest) {
			users.add(getUserById(p.getUserId()));
		}
		return users;
	}
	
	public void updateUser(User user) {
		log.info("Updating stats for user " + user.getUserId());
		delegate.updateUser(user);
	}
	
	public void updateUsers(List<User> users) {
		delegate.updateUsers(users);
	}
	

}
