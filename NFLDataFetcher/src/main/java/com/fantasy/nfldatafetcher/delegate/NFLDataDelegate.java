package com.fantasy.nfldatafetcher.delegate;

import org.springframework.stereotype.Component;

import com.jaunt.JauntException;
import com.jaunt.UserAgent;

@Component
public class NFLDataDelegate {

	protected String getJson(String url) {
		  try{
				UserAgent userAgent = new UserAgent();
				userAgent.sendGET(url);
				String json = userAgent.json.toString();
				return json;
			}
			catch(JauntException e){
				System.err.println(e);
				return null;
			}
	}
	
}
