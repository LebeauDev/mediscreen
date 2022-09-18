package com.openclassrooms.mediscreenRisque.entity;

public class Patient {
	
	private int id;
	private String family;
	private String given;
	private String dob;
	private String sex;
	private String address;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Patient [id=" + id + ", family=" + family + ", given=" + given + ", dob=" + dob + ", sex=" + sex
				+ ", address=" + address + ", phone=" + phone + "]";
	}

}
