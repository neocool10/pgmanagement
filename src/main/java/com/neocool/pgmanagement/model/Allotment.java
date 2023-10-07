package com.neocool.pgmanagement.model;

public class Allotment {
    private int allotmentId;
    private String name;
    private String flatno;
    private String rent;
    private String agreement;
    private String startDate;
    private String endDate;
    
    
    
    public Allotment() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Allotment(int allotmentId, String name, String flatno, String rent, String agreement, String startDate,
			String endDate) {
		super();
		this.allotmentId = allotmentId;
		this.name = name;
		this.flatno = flatno;
		this.rent = rent;
		this.agreement = agreement;
		this.startDate = startDate;
		this.endDate = endDate;
	}



	public int getAllotmentId() {
		return allotmentId;
	}



	public void setAllotmentId(int allotmentId) {
		this.allotmentId = allotmentId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
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



	public String getAgreement() {
		return agreement;
	}



	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}



	public String getStartDate() {
		return startDate;
	}



	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}



	public String getEndDate() {
		return endDate;
	}



	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
    
	

    

}
