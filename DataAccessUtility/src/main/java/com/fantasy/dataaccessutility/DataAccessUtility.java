package com.fantasy.dataaccessutility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.manager.MetadataDataAccessManager;
import com.fantasy.dataaccessutility.manager.ModifierDataAccessManager;
import com.fantasy.dataaccessutility.manager.PlayerDataAccessManager;
import com.fantasy.dataaccessutility.manager.UserDataAccessManager;
import com.fantasy.dataaccessutility.model.Metadata;
import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.User;
import com.fantasy.dataaccessutility.model.modifier.Modifier;

@Component
public class DataAccessUtility {

	@Autowired
	private PlayerDataAccessManager playerManager;

	@Autowired
	private UserDataAccessManager userManager;
	
	@Autowired
	private ModifierDataAccessManager modifierManager;

	@Autowired
	private MetadataDataAccessManager metadataIdManager;
	

						// PLAYERS
	public Player getPlayerById(String playerId) {
		return playerManager.getPlayerById(playerId);
	}
	public Player getPlayerFromPlayer(Player player) {
		return playerManager.getPlayerFromPlayer(player);
	}
	public List<Player> getPlayersByIdList(List<String> playerIds) {
		return playerManager.getPlayersByIdList(playerIds);
	}
	public List<Player> getPlayersFromPlayerList(List<Player> playersRequest) {
		return playerManager.getPlayersFromPlayerList(playersRequest);
	}
	public List<Player> getAllPlayersByPosition(String position) {
		return playerManager.getAllPlayersByPosition(position);
	}
	public void updatePlayer(Player player) {
		playerManager.updatePlayer(player);
	}
	public void updatePlayers(List<Player> players) {
		playerManager.updatePlayers(players);
	}


						// USERS
	public User getUserById(String userId) {
		return userManager.getUserById(userId);
	}
	public User getUserFromUser(User user) {
		return userManager.getUserFromUser(user);
	}
	public List<User> getUsersByIdList(List<String> userIds) {
		return userManager.getUsersByIdList(userIds);
	}
	public List<User> getUsersFromUserList(List<User> usersRequest) {
		return userManager.getUsersFromUserList(usersRequest);
	}
	public void updateUser(User user) {
		userManager.updateUser(user);
	}
	public void updateUsers(List<User> users) {
		userManager.updateUsers(users);
	}
	
	
			//MODIFIERS
	public Modifier getModifier(String modifierId) {
		return modifierManager.getModifierById(modifierId);
	}
	
	public List<Modifier> getAllModifiers() {
		return modifierManager.getAllModifiers();
	}
	
	//TODO make connection to Metadata database so it can be used in modife

		
		// METADATA
	public Metadata getMetadata(String metadataId) {
		return metadataIdManager.getMetadataById(metadataId);
	}
	
	public boolean updateMetadata(Metadata metadata) {
		return metadataIdManager.updateMetadata(metadata);
	}
}
