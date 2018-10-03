package com.fantasy.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

import com.fantasy.dataaccessutility.model.team.NflTeam;
import com.jaunt.Element;
import com.jaunt.UserAgent;

public class TestApp {
	
	static SortedMap<String, String> mascotToTeamNameMapping;
	
	static {
			mascotToTeamNameMapping = new TreeMap<String, String>();
			mascotToTeamNameMapping.put("49ers", "SF");
			mascotToTeamNameMapping.put("Bears", "CHI");
			mascotToTeamNameMapping.put("Bengals", "CIN");
			mascotToTeamNameMapping.put("Bills", "BUF");
			mascotToTeamNameMapping.put("Broncos", "DEN");
			mascotToTeamNameMapping.put("Browns", "CLE");
			mascotToTeamNameMapping.put("Buccaneers", "TB");
			mascotToTeamNameMapping.put("Cardinals", "ARI");
			mascotToTeamNameMapping.put("Chargers", "LAC");
			mascotToTeamNameMapping.put("Chiefs", "KC");
			mascotToTeamNameMapping.put("Colts", "IND");
			mascotToTeamNameMapping.put("Cowboys", "DAL");
			mascotToTeamNameMapping .put("Dolphins", "MIA");
			mascotToTeamNameMapping.put("Eagles", "PHI");
			mascotToTeamNameMapping.put("Falcons", "ATL");
//			mascotToTeamNameMapping.put("Free Agent", "FA");
			mascotToTeamNameMapping.put("Giants", "NYG");
			mascotToTeamNameMapping.put("Jaguars", "JAC");
			mascotToTeamNameMapping.put("Jets", "NYJ");
			mascotToTeamNameMapping.put("Lions", "DET");
			mascotToTeamNameMapping.put("Packers", "GB");
			mascotToTeamNameMapping.put("Panthers", "CAR");
			mascotToTeamNameMapping.put("Patriots", "NE");
			mascotToTeamNameMapping.put("Raiders", "OAK");
			mascotToTeamNameMapping.put("Rams", "LAR");
			mascotToTeamNameMapping.put("Ravens", "BAL");
			mascotToTeamNameMapping.put("Redskins", "WAS");
			mascotToTeamNameMapping.put("Saints", "NO");
			mascotToTeamNameMapping.put("Seahawks", "SEA");
			mascotToTeamNameMapping.put("Steelers", "PIT");
			mascotToTeamNameMapping.put("Texans", "HOU");
			mascotToTeamNameMapping.put("Titans", "TEN");
			mascotToTeamNameMapping.put("Vikings", "MIN");
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("src/main/resources/nfl-team-data.csv"));
		
		SortedMap<String, Teamy> map = new TreeMap<String, Teamy>();
		
		while (s.hasNextLine()) {
			String line = s.nextLine();
			List<String> split = Arrays.asList(line.split(" "));
			String mascot = split.get(split.size() - 1);
			List<String> splitCity = split.subList(0, (split.size() - 1));
			String city = "";
			for (String str : splitCity) {
				city += str + " ";
			}
			city = city.trim();
			map.put(mascot, new Teamy(city, mascot));
		}

		UserAgent u = new UserAgent();
		try {
			u.open(new File("src/main/resources/logos.html"));
			Element logowall = u.doc.findFirst("<ul class=\"logoWall\">");
			for (Element img : logowall.findEvery("<li>")) {
				String src = img.findFirst("<img>").getAt("src");
				String teamFull = img.findFirst("<a>").getAt("title");
				String team = teamFull.replace(" Logos", "");
				List<String> split = Arrays.asList(team.split(" "));
				String mascot = split.get(split.size() - 1);
				Teamy t = map.get(mascot);
				t.src = src;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int count = 1;
		for (String teamMascot : mascotToTeamNameMapping.keySet()) {
			Teamy t = map.get(teamMascot);
			NflTeam team = new NflTeam(String.valueOf(count++), t.name, mascotToTeamNameMapping.get(t.mascot), t.city, t.mascot, t.src);
			System.out.println("nflTeams.put(\"" + team.getNflTeamId() + "\", new NflTeam(\"" + team.getNflTeamId() + "\", \"" + team.getNflTeamName() + "\", \"" + team.getAbbreviation() + "\", \"" + team.getCity() + "\", \"" + team.getMascot() + "\", \"" + team.getLogoIconUrl() + "\"));");
		}

	}

}

class Teamy {
	
	String city;
	String mascot;
	String src;
	String name;
	
	public Teamy(String city, String mascot) {
		super();
		this.city = city;
		this.mascot = mascot;
		this.name = city + " " + mascot;
	}
	
}
