package com.fah.enterprises.models;

public class RegistrationRequest {
    
	private String username;
    private String password;
    private String firstName;

    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //need default constructor for JSON Parsing
    public RegistrationRequest()
    {

    }

    public RegistrationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }
}
