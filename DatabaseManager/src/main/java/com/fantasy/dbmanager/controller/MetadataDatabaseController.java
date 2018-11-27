package com.fantasy.dbmanager.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.dataaccessutility.model.Metadata;
import com.fantasy.dbmanager.manager.MetadataDatabaseManager;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/metadata")
public class MetadataDatabaseController  {
	
	private static Logger log = Logger.getLogger(MetadataDatabaseController.class);
	
	@Autowired
	private MetadataDatabaseManager manager;
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
    public boolean updateMetadata(@RequestBody Metadata metadata) {
    	log.info("DatabaseManager :: updating or adding metadata: key=" + metadata.getMetadataId() + ", value=" + metadata.getValue());
    	boolean success = manager.updateMetadata(metadata);
    	log.info("DatabaseManager :: success=" + success);
    	return success;
    }
	
	@RequestMapping(value = "/get/{metadataId}", method = RequestMethod.GET, produces="application/json")
    public Metadata getMetadata(@PathVariable String metadataId) {
    	log.info("DatabaseManager :: getting " + metadataId + " from metadata database...");
    	try {
    		Metadata metadata = manager.get(metadataId);
    		log.info("DatabaseManager :: success :: key=" + metadata.getMetadataId() + ", value=" + metadata.getValue());
    		return metadata;
    	} catch (Exception e) {
    		log.error("ERROR :: error occured trying to get metadata: " + metadataId, e);
    		return null;
    	}
    }
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces="application/json")
    public List<Metadata> getAllMetadata() {
    	log.info("DatabaseManager :: getting all metadata entries from database...");
    	List<Metadata> list = manager.getAll();
    	if (CollectionUtils.isEmpty(list)) {
    		log.warn("DatabaseManager :: No metadata found in database");
    	} else {
    		log.info("DatabaseManager :: success :: got " + list.size() + " metadata entries from database");
    	}
		return list;
    }
	
    @RequestMapping("/init")
    public boolean initializeMetadata() {
    	log.info("DatabaseManager :: initializing default metadata values in database...");
    	boolean success = manager.initializeDefaults();
    	log.info("DatabaseManager :: success = " + success);
    	return success;
    }
    
    @RequestMapping("/clear")
    public boolean clearMetadata() {
    	log.info("DatabaseManager :: clearing all metadata entries in database...");
    	boolean success = manager.clear();
    	log.info("DatabaseManager :: success = " + success);
    	return success;
    }

}