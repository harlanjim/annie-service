package com.fah.enterprises.models;

public class RegistrationRequest {
    
	private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String phoneType;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
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
