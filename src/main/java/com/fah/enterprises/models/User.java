package com.fah.enterprises.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="annieuser")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false)
	private List<Role> roles;

	@Column(name = "active")
	private boolean active;
	
	@Column(name = "last_login_ts", nullable = true)
	private Date lastLoginTs;
	
	@Column(name = "activation_req_ts", nullable = true)
	private Date activationRequestTs;

	@Column(name = "password_reset_req_ts", nullable = true)
	private Date passwordResetRequestTs;
	
	@Column(name = "bad_auth_reqs", nullable = true)
	private Long badAuthenticationRequests;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public Date getLastLoginTs() {
		return lastLoginTs;
	}

	public void setLastLoginTs(Date lastLoginTs) {
		this.lastLoginTs = lastLoginTs;
	}

	public Date getActivationRequestTs() {
		return activationRequestTs;
	}

	public void setActivationRequestTs(Date activationRequestTs) {
		this.activationRequestTs = activationRequestTs;
	}

	public Date getPasswordResetRequestTs() {
		return passwordResetRequestTs;
	}

	public void setPasswordResetRequestTs(Date passwordResetRequestTs) {
		this.passwordResetRequestTs = passwordResetRequestTs;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Long getBadAuthenticationRequests() {
		return badAuthenticationRequests;
	}

	public void setBadAuthenticationRequests(Long badAuthenticationRequests) {
		this.badAuthenticationRequests = badAuthenticationRequests;
	}

}

