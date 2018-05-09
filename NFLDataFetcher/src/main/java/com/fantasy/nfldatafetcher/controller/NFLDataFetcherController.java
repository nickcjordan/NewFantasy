package com.fantasy.nfldatafetcher.controller;



import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.nfldatafetcher.adapter.NFLDataAdapter;
import com.fantasy.nfldatafetcher.model.NFLPlayerSeasonStats;

@RestController
@RequestMapping("/nfl")
public class NFLDataFetcherController  {
	
	private static Logger log = Logger.getLogger(NFLDataFetcherController.class);
	
	@Autowired
	private NFLDataAdapter adapter;
	
    @RequestMapping("/getAllPlayerData")
    public Map<String, NFLPlayerSeasonStats> getPlayerData() {
    	log.info("NFLDataFetcher :: getting player data...");
    	Map<String, NFLPlayerSeasonStats> data = adapter.getPlayerData();
    	log.info("NFLDataFetcher :: success :: got data for [" + data.size() + "] players");
    	return data;
    }

}