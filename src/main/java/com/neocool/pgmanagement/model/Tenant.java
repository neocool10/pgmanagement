package com.neocool.pgmanagement.model;

public class Tenant {
	private int tenantId;
	private String tenantName;
	private String tenantPhone;
	private String tenantCity;
	private String tenantDate;
	private String tenantAadhar;
	private String tenantEmail;
	
	
	
	public Tenant() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Tenant(int tenantId, String tenentName, String tenantPhone, String tenantCity, String tenantDate,
			String tenantAadhar, String tenantEmail) {
		super();
		this.tenantId = tenantId;
		this.tenantName = tenentName;
		this.tenantPhone = tenantPhone;
		this.tenantCity = tenantCity;
		this.tenantDate = tenantDate;
		this.tenantAadhar = tenantAadhar;
		this.tenantEmail = tenantEmail;
	}
	public int getTenantId() {
		return tenantId;
	}
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}
	public String getTenantName() {
		return tenantName;
	}
	public void setTenantName(String tenentName) {
		this.tenantName = tenentName;
	}
	public String getTenantPhone() {
		return tenantPhone;
	}
	public void setTenantPhone(String tenantPhone) {
		this.tenantPhone = tenantPhone;
	}
	public String getTenantCity() {
		return tenantCity;
	}
	public void setTenantCity(String tenantCity) {
		this.tenantCity = tenantCity;
	}
	public String getTenantDate() {
		return tenantDate;
	}
	public void setTenantDate(String tenantDate) {
		this.tenantDate = tenantDate;
	}
	public String getTenantAadhar() {
		return tenantAadhar;
	}
	public void setTenantAadhar(String tenantAadhar) {
		this.tenantAadhar = tenantAadhar;
	}
	public String getTenantEmail() {
		return tenantEmail;
	}
	public void setTenantEmail(String tenantEmail) {
		this.tenantEmail = tenantEmail;
	}
	
	
}
