package com.kang.test.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;


public class PersistentLogins implements java.io.Serializable {

	// Fields

	private String series;
	private String username;
	private String token;
	private Timestamp lastUsed;

	// Constructors

	/** default constructor */
	public PersistentLogins() {
	}

	/** full constructor */
	public PersistentLogins(String username, String token, Timestamp lastUsed) {
		this.username = username;
		this.token = token;
		this.lastUsed = lastUsed;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Timestamp lastUsed) {
		this.lastUsed = lastUsed;
	}

	
	
	
	

}