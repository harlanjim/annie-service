package com.fah.enterprises.models;

import java.util.List;


public class ProfileApi {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String city;
	private String state;
	private String country;
	private List<Phone> phone;

	public ProfileApi() {
		super();
	}
	
	public ProfileApi(Profile profile) {
		super();
		this.id = profile.getId();
		this.firstName = profile.getFirstName();
		this.lastName = profile.getLastName();
		this.email = profile.getEmail();
		this.address = profile.getAddress();
		this.city = profile.getCity();
		this.state = profile.getState();
		this.country = profile.getCountry();
		this.phone = profile.getPhone();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Phone> getPhone() {
		return phone;
	}

	public void setPhone(List<Phone> phone) {
		this.phone = phone;
	}

}
