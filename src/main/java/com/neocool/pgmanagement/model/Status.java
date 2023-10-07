package com.neocool.pgmanagement.model;

public class Status {
	private int id;
	private String flatno;
	private String rent;
	private String maintenance;
	private String parking;
	
	
	
	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Status(int id, String flatno, String rent, String maintenance, String parking) {
		super();
		this.id = id;
		this.flatno = flatno;
		this.rent = rent;
		this.maintenance = maintenance;
		this.parking = parking;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFlatno() {
		return flatno;
	}
	public void setFlatno(String flatno) {
		this.flatno = flatno;
	}
	public String getRent() {
		return rent;
	}
	public void setRent(String rent) {
		this.rent = rent;
	}
	public String getMaintenance() {
		return maintenance;
	}
	public void setMaintenance(String maintenance) {
		this.maintenance = maintenance;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	
	
}
