package com.fantasy.dataaccessutility.model.team;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class Team {

	private String teamId;
	private String teamName;
	private String city;
	private String fullName;
	private String abbrev;
	private Roster roster;
	
	public Team() { 
		roster = new Roster();
	}
	
	public Team(String name, String city, String id, String abbrev) {
		this.teamName = name;
		this.abbrev = abbrev;
		this.teamId = id;
		this.city = city;
		this.fullName = city + " " + name;
		roster = new Roster();
	}

	public Team(String id, String userName) {
		this.teamId = id;
		this.teamName = userName;
		roster = new Roster();
	}

	public String getAbbrev() {
		return abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getId() {
		return teamId;
	}

	public void setId(String id) {
		this.teamId = id;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String name) {
		this.teamName = name;
	}

	public String getFullName() {
		return fullName;
	}
	
	public Roster getRoster() {
		return roster;
	}

	public void setRoster(Roster roster) {
		this.roster = roster;
	}
	
// TODO bridge the gap between the jackson mapping of this class to the new lineup and bench structure
	
	
//	public void addQb(Player player){
//		roster.qb.add(player);
//	}
//	
//	public void addRb(Player player){
//		rb.add(player);
//	}
//	
//	public void addWr(Player player){
//		wr.add(player);
//	}
//	
//	public void addTe(Player player){
//		te.add(player);
//	}
//	
//	public void addK(Player player){
//		k.add(player);
//	}
//	
//	public void addD(Player player){
//		d.add(player);
//	}

//
//	public List<Player> getQb() {
//		return qb;
//	}
//
//	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
//	public void setQb(List<Player> qb) {
//		this.qb = qb;
//	}
//
//	public List<Player> getRb() {
//		return rb;
//	}
//
//	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
//	public void setRb(List<Player> rb) {
//		this.rb = rb;
//	}
//
//	public List<Player> getWr() {
//		return wr;
//	}
//
//	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
//	public void setWr(List<Player> wr) {
//		this.wr = wr;
//	}
//
//	public List<Player> getTe() {
//		return te;
//	}
//
//	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
//	public void setTe(List<Player> te) {
//		this.te = te;
//	}
//
//	public List<Player> getK() {
//		return k;
//	}
//
//	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
//	public void setK(List<Player> k) {
//		this.k = k;
//	}
//
//	public List<Player> getD() {
//		return d;
//	}
//
//	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
//	public void setD(List<Player> d) {
//		this.d = d;
//	}


//	@JsonIgnore
//	public List<List<Player>> getListOfPlayersByPosition() {
//		List<List<Player>> positions = new ArrayList<List<Player>>();
//		positions.add(qb);
//		positions.add(rb);
//		positions.add(wr);
//		positions.add(te);
//		positions.add(k);
//		positions.add(d);
//		return positions;
//	}
//	@JsonIgnore
//	public List<Player> getListOfPlayers() {
//		List<Player> players = new ArrayList<Player>();
//		for (List<Player> positionGroup : getListOfPlayersByPosition()) {
//			for (Player player : positionGroup) {
//				players.add(player);
//			}
//		}
//		return players;
//	}
//	@JsonIgnore
//	public Map<String, Player> getPlayerMap() {
//		HashMap<String, Player> allPlayers = new HashMap<String, Player>();
//		for (Player p : getListOfPlayers()) {
//			allPlayers.put(p.getPlayerId(), p);
//		}
//		return allPlayers;
//	}
	
	
	
}
