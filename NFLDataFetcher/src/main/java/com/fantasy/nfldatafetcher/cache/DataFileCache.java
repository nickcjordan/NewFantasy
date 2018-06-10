package com.fantasy.nfldatafetcher.cache;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fantasy.nfldatafetcher.model.NFLPlayerSeasonStats;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DataFileCache {
	
	private static Logger log = Logger.getLogger(DataFileCache.class);
	
	private static final String CACHED_DATA_PATH = "src/main/resources/stats/getAllPlayerData.json";

	public Map<String, NFLPlayerSeasonStats> getCachedData() {
		Map<String, NFLPlayerSeasonStats> map = null;
		try {
			map = new ObjectMapper().readValue(new File(CACHED_DATA_PATH), new TypeReference<Map<String,NFLPlayerSeasonStats>>(){});
		} catch (IOException e) {
			log.error(e);
		}
		return map;
	}

	public void updateCacheWithData(Map<String, NFLPlayerSeasonStats> seasonStatsMap) {
		log.info("updating data in cache..." );
		try {
			new ObjectMapper().writeValue(new File(CACHED_DATA_PATH), seasonStatsMap);
		} catch (IOException e) {
			log.error(e);
		}
	}

}