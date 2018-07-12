package com.fantasy.dbmanager.model;

import java.util.List;
import java.util.Map;

import com.fantasy.dataaccessutility.model.Player;

public class TeamTO {
	
	private String id;
	private String name;
	private List<String> qb;
	private List<String> rb;
	private List<String> wr;
	private List<String> te;
	private List<String> k;
	private List<String> d;
	private String city;
	private String fullName;
	private String abbrev;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getQb() {
		return qb;
	}
	public void setQb(List<String> qb) {
		this.qb = qb;
	}
	public List<String> getRb() {
		return rb;
	}
	public void setRb(List<String> rb) {
		this.rb = rb;
	}
	public List<String> getWr() {
		return wr;
	}
	public void setWr(List<String> wr) {
		this.wr = wr;
	}
	public List<String> getTe() {
		return te;
	}
	public void setTe(List<String> te) {
		this.te = te;
	}
	public List<String> getK() {
		return k;
	}
	public void setK(List<String> k) {
		this.k = k;
	}
	public List<String> getD() {
		return d;
	}
	public void setD(List<String> d) {
		this.d = d;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAbbrev() {
		return abbrev;
	}
	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}
	
}
