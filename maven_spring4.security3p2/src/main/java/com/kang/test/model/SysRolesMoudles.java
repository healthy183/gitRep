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


public class SysRolesMoudles implements java.io.Serializable {
	
	private String id;
	private SysRoles sysRoles;
	private SysModules sysModules;

	// Constructors

	/** default constructor */
	public SysRolesMoudles() {
	}

	/** full constructor */
	public SysRolesMoudles(SysRoles sysRoles, SysModules sysModules) {
		this.sysRoles = sysRoles;
		this.sysModules = sysModules;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SysRoles getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(SysRoles sysRoles) {
		this.sysRoles = sysRoles;
	}

	public SysModules getSysModules() {
		return sysModules;
	}

	public void setSysModules(SysModules sysModules) {
		this.sysModules = sysModules;
	}

	
	
	
}