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


public class SysRolesAuthorities implements java.io.Serializable {
	
	private String id;
	private SysAuthorities sysAuthorities;
	private SysRoles sysRoles;

	// Constructors

	/** default constructor */
	public SysRolesAuthorities() {
	}

	/** full constructor */
	public SysRolesAuthorities(SysAuthorities sysAuthorities, SysRoles sysRoles) {
		this.sysAuthorities = sysAuthorities;
		this.sysRoles = sysRoles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SysAuthorities getSysAuthorities() {
		return sysAuthorities;
	}

	public void setSysAuthorities(SysAuthorities sysAuthorities) {
		this.sysAuthorities = sysAuthorities;
	}

	public SysRoles getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(SysRoles sysRoles) {
		this.sysRoles = sysRoles;
	}
	
	
	
}