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


public class SysAuthoritiesResources implements java.io.Serializable {
	
	private String id;
	private SysAuthorities sysAuthorities;
	private SysResources sysResources;

	// Constructors
	/** default constructor */
	public SysAuthoritiesResources() {
	}

	/** full constructor */
	public SysAuthoritiesResources(SysAuthorities sysAuthorities,
			SysResources sysResources) {
		this.sysAuthorities = sysAuthorities;
		this.sysResources = sysResources;
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

	public SysResources getSysResources() {
		return sysResources;
	}

	public void setSysResources(SysResources sysResources) {
		this.sysResources = sysResources;
	}
	
	
	
	
}