package com.neocool.pgmanagement.model;

public class Flats {
    private String flatno;
    private String stat;
    
    
    
	public Flats() {
		super();
	}


	public Flats(String flatno) {
		super();
		this.flatno = flatno;
	}


	public Flats(String flatno, String stat) {
		super();
		this.flatno = flatno;
		this.stat = stat;
	}
	
	
	public String getFlatno() {
		return flatno;
	}
	public void setFlatno(String flatno) {
		this.flatno = flatno;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
    

    
    
}
