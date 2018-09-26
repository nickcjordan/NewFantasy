package com.fantasy.dbmanager.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.dataaccessutility.model.modifier.Modifier;
import com.fantasy.dataaccessutility.model.ui.ModifierUpdateRequest;
import com.fantasy.dbmanager.manager.ModifierDatabaseManager;
import com.fantasy.dbmanager.processor.ModifierUpdateRequestProcessor;

@RestController
@RequestMapping("/modifier")
public class ModifierDatabaseController  {
	
	private static Logger log = Logger.getLogger(ModifierDatabaseController.class);
	
	@Autowired
	private ModifierDatabaseManager modifierManager;
	
	@Autowired
	private ModifierUpdateRequestProcessor processor;
	
	@RequestMapping(value = "/updateList", method = RequestMethod.POST)
    public boolean putModifier(@RequestBody List<Modifier> modifiers) {
    	log.info("DatabaseManager :: putting " + modifiers.size() + " modifiers in database...");
    	modifierManager.put(modifiers);
    	log.info("DatabaseManager :: success :: put " + modifiers.size() + " modifiers in database");
    	return true;
    }

    @RequestMapping("/count")
    public long count() {
    	log.info("DatabaseManager :: getting modifier count...");
    	long count = modifierManager.count();
    	log.info("DatabaseManager :: success :: count was [" + count + "]");
    	return count;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public boolean updateModifier(@RequestBody ModifierUpdateRequest modifierUpdateRequest) {
    	log.info("DatabaseManager :: updating modifier [userId:" + modifierUpdateRequest.getUserId() + ", modifierId:" + modifierUpdateRequest.getModifierId() + "] in database...");
    	processor.processRequest(modifierUpdateRequest);
    	log.info("DatabaseManager :: success :: updated modifier [userId:" + modifierUpdateRequest.getUserId() + ", modifierId:" + modifierUpdateRequest.getModifierId() + "] in database...");
    	return true;
    }
    
    @RequestMapping("/getAll")
    public List<Modifier> getAll() {
    	log.info("DatabaseManager :: getting all modifiers...");
    	List<Modifier> modifiers = modifierManager.getAll();
    	log.info("DatabaseManager :: success :: got [" + modifiers.size() + "] modifiers");
    	return modifiers;
    }
    
    @RequestMapping("/clear")
    public boolean removeAllModifiers() {
    	log.info("DatabaseManager :: removing all modifiers from database...");
    	boolean success = modifierManager.clear();
    	log.info("DatabaseManager :: success = " + success);
    	return success;
    }

}