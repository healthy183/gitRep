package com.kang.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

public class SysUsersRoles implements java.io.Serializable {
	
	private String id;
	private SysUsers sysUsers;
	private SysRoles sysRoles;

	// Constructors

	/** default constructor */
	public SysUsersRoles() {
	}

	/** full constructor */
	public SysUsersRoles(SysUsers sysUsers, SysRoles sysRoles) {
		this.sysUsers = sysUsers;
		this.sysRoles = sysRoles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SysUsers getSysUsers() {
		return sysUsers;
	}

	public void setSysUsers(SysUsers sysUsers) {
		this.sysUsers = sysUsers;
	}

	public SysRoles getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(SysRoles sysRoles) {
		this.sysRoles = sysRoles;
	}
	
	
	
	
	
}