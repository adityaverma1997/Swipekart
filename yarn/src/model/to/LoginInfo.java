package model.to;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LoginInfo implements Serializable{
	@Id
	@Column(length=100)
	private String username;
	
	@Column(length=100)
	private String password;
	
	@Column(length=100)
	private String rolename;
	
	@Column(length=100,unique=true)
	private String emailid;
	
	@Column(length=300)
	private String sq;
	
	@Column(length=100)
	private String sans;
	
	@Column(nullable=true)
	private Timestamp lastlogin;

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

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getSq() {
		return sq;
	}

	public void setSq(String sq) {
		this.sq = sq;
	}

	public String getSans() {
		return sans;
	}

	public void setSans(String sans) {
		this.sans = sans;
	}

	public Timestamp getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Timestamp lastlogin) {
		this.lastlogin = lastlogin;
	}
}
