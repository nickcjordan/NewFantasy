package com.fantasy.nfldatafetcher.delegate;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fantasy.nfldatafetcher.model.advancedstats.APIAdvancedStatsResponse;
import com.fantasy.nfldatafetcher.model.basicstats.APIBasicStatsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;

@Component
public class NFLAdvancedStatsDelegate extends NFLDataDelegate {

	private static Logger log = Logger.getLogger(NFLAdvancedStatsDelegate.class);
	
	public APIAdvancedStatsResponse getNFLPlayerAdvancedStatsResponse(int week, String pos) {
		log.debug("NFLDataFetcher :: getting advanced stats...");
		String json = getJson(NFLDataUrlBuilder.buildAdvancedStatsUrl(week, pos));
		
		APIAdvancedStatsResponse response = null;
		try {
			response = new ObjectMapper().readValue(json, APIAdvancedStatsResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
