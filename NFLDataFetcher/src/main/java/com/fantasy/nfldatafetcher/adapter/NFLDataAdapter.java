package com.fantasy.nfldatafetcher.adapter;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fantasy.nfldatafetcher.cache.DataFileCache;
import com.fantasy.nfldatafetcher.model.NFLPlayerSeasonStats;
import com.fantasy.nfldatafetcher.processor.DataRetriever;

@Component
public class NFLDataAdapter {
	
	private static Logger log = Logger.getLogger(DataFileCache.class);
	
	@Value("${data.useCache}")
	private boolean useCachedData;
	
	@Autowired
	private DataFileCache cache;
	
	@Autowired
	private DataRetriever retriever;
	
	public Map<String, NFLPlayerSeasonStats> getPlayerData() {
		Map<String, NFLPlayerSeasonStats> data = null;
		if (useCachedData) {
			log.info("useCachedData = true :: getting cached data..." );
			data = cache.getCachedData();
		} else {
			log.info("useCachedData = false :: retrieving current data..." );
			data = retriever.retrieveCurrentData();
			cache.updateCacheWithData(data);
		}
		return  data;
	}
	
}
