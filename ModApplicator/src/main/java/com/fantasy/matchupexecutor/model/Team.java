package com.fantasy.matchupexecutor.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.fantasy.matchupexecutor.model.modifier.ModifiableTarget;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Team implements ModifiableTarget {

	private int id;
	private String name;
	private List<Player> qb;
	private List<Player> rb;
	private List<Player> wr;
	private List<Player> te;
	private List<Player> k;
	private List<Player> d;
	private String city;
	private String fullName;
	private String abbrev;
	
	public Team() { 
		qb = new ArrayList<Player>();
		rb = new ArrayList<Player>();
		wr = new ArrayList<Player>();
		te = new ArrayList<Player>();
		k = new ArrayList<Player>();
		d = new ArrayList<Player>();
	}
	
	public Team(String name, String city, int id, String abbrev) {
		this.name = name;
		this.abbrev = abbrev;
		qb = new ArrayList<Player>();
		rb = new ArrayList<Player>();
		wr = new ArrayList<Player>();
		te = new ArrayList<Player>();
		k = new ArrayList<Player>();
		d = new ArrayList<Player>();
		this.id = id;
		this.city = city;
		this.fullName = city + " " + name;
	}

	public Team(int id, String userName) {
		this.id = id;
		this.name = userName;
		this.qb = new ArrayList<Player>();
		this.rb = new ArrayList<Player>();
		this.wr = new ArrayList<Player>();
		this.te = new ArrayList<Player>();
		this.k = new ArrayList<Player>();
		this.d = new ArrayList<Player>();
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


	public int getNumberOfPlayersOnTeam() {
		return qb.size() + rb.size() + wr.size() + te.size() + k.size() + d.size();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void addQb(Player player){
		qb.add(player);
	}
	
	public void addRb(Player player){
		rb.add(player);
	}
	
	public void addWr(Player player){
		wr.add(player);
	}
	
	public void addTe(Player player){
		te.add(player);
	}
	
	public void addK(Player player){
		k.add(player);
	}
	
	public void addD(Player player){
		d.add(player);
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Player> getQb() {
		return qb;
	}

	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	public void setQb(List<Player> qb) {
		this.qb = qb;
	}

	public List<Player> getRb() {
		return rb;
	}

	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	public void setRb(List<Player> rb) {
		this.rb = rb;
	}

	public List<Player> getWr() {
		return wr;
	}

	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	public void setWr(List<Player> wr) {
		this.wr = wr;
	}

	public List<Player> getTe() {
		return te;
	}

	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	public void setTe(List<Player> te) {
		this.te = te;
	}

	public List<Player> getK() {
		return k;
	}

	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	public void setK(List<Player> k) {
		this.k = k;
	}

	public List<Player> getD() {
		return d;
	}

	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	public void setD(List<Player> d) {
		this.d = d;
	}

	public String getFullName() {
		return fullName;
	}

	public List<List<Player>> getListOfPlayersByPosition() {
		List<List<Player>> positions = new ArrayList<List<Player>>();
		positions.add(qb);
		positions.add(rb);
		positions.add(wr);
		positions.add(te);
		positions.add(k);
		positions.add(d);
		return positions;
	}

	public List<Player> getListOfPlayers() {
		List<Player> players = new ArrayList<Player>();
		for (List<Player> positionGroup : getListOfPlayersByPosition()) {
			for (Player player : positionGroup) {
				players.add(player);
			}
		}
		return players;
	}
	
}
