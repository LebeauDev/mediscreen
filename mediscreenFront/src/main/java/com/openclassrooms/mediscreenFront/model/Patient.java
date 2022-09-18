package com.openclassrooms.mediscreenFront.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true) 
@JsonDeserialize
public class Patient {
	
	//@JsonProperty("id")
	//private String id;
	@JsonProperty("family")
	private String family;
	@JsonProperty("given")
	private String given;
	@JsonProperty("dob")
    private String dob;
	@JsonProperty("sex")
	private String sex;
	@JsonProperty("address")
	private String address;
	@JsonProperty("phone")
	private String phone;
	
	

	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getGiven() {
		return given;
	}
	public void setGiven(String given) {
		this.given = given;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/*
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}*/
	
	@Override
	public String toString() {
		return "Patient [id=" + ", family=" + family + ", given=" + given + ", dob=" + dob + ", sex=" + sex
				+ ", address=" + address + ", phone=" + phone + "]";
	}

}
