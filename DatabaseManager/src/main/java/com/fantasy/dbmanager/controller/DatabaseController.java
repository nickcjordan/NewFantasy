package com.fantasy.dbmanager.controller;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.dbmanager.manager.PlayerDatabaseManager;
import com.fantasy.dbmanager.model.Player;

@RestController
public class DatabaseController  {
	
	@Autowired
	private PlayerDatabaseManager playerManager;

    @RequestMapping("/count")
    public long count() {
    	return playerManager.count();
    }
    
    @RequestMapping("/put")
    public String putPlayer() {
    	playerManager.putPlayer();
    	return "Success";
    }
    
    @RequestMapping("/getAll")
    public List<Player> getAll() {
    	return playerManager.getAll();
    }
    
    @RequestMapping("/get")
    public Player getAll(@RequestParam(value="id") int id) {
    	System.out.println("getting for id=" + id);
    	return playerManager.get(id);
    }

}