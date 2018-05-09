package com.fantasy.nfldatafetcher.delegate;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fantasy.nfldatafetcher.model.advancedstats.APIAdvancedStatsResponse;
import com.fantasy.nfldatafetcher.model.basicstats.APIBasicStatsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;

@Component
public class NFLBasicStatsDelegate extends NFLDataDelegate {

	private static Logger log = Logger.getLogger(NFLBasicStatsDelegate.class);
	
	public APIBasicStatsResponse getNFLPlayerBasicStatsResponse(int week, String pos) {
		log.debug("NFLDataFetcher :: getting basic stats...");
		String json = getJson(NFLDataUrlBuilder.buildBasicStatsUrl(week, pos));
		
		APIBasicStatsResponse response = null;
		try {
			response = new ObjectMapper().readValue(json, APIBasicStatsResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
}
