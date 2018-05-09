package com.fantasy.nfldatafetcher.delegate;

public class NFLDataUrlBuilder {
	
	private static final String SEASON_YEAR = "2017";
	
	private static final String URL_BASE = "http://api.fantasy.nfl.com/v1/players";
	private static final String AND = "&";

	private static final String BASIC_STATS_BASE_PATH = "/stats?statType=seasonStats";
	private static final String ADVANCED_STATS_BASE_PATH = "/advanced?";
	
	private static final String POSITION_PARAM = "position=";
	private static final String WEEK_PARAM = "week=";
	private static final String SEASON_PARAM = "season=";
	private static final String FORMAT_PARAM = "format=json";
	
	public static String buildBasicStatsUrl(int week, String pos) {
		return new StringBuilder().append(URL_BASE)
				.append(BASIC_STATS_BASE_PATH)
				.append(AND).append(SEASON_PARAM).append(SEASON_YEAR)
				.append(AND).append(WEEK_PARAM).append(week)
				.append(AND).append(POSITION_PARAM).append(pos)
				.append(AND).append(FORMAT_PARAM)
				.toString();
	}

	public static String buildAdvancedStatsUrl(int week, String pos) {
		return new StringBuilder().append(URL_BASE)
				.append(ADVANCED_STATS_BASE_PATH)
				.append(SEASON_PARAM).append(SEASON_YEAR)
				.append(AND).append(WEEK_PARAM).append(week)
				.append(AND).append(POSITION_PARAM).append(pos)
				.append(AND).append(FORMAT_PARAM)
				.toString();
	}

}
