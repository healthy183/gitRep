package com.kang.test.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;


public class SysAuthorities  implements java.io.Serializable {
	
	private String authorityId;
	private String authorityMark;
	private String authorityName;
	private String authorityDesc;
	private String message;
	private Integer enable;
	private Integer issys;
	private String moduleId;
	private Set<SysRolesAuthorities> sysRolesAuthoritieses = new HashSet<SysRolesAuthorities>(
			0);
	private Set<SysAuthoritiesResources> sysAuthoritiesResourceses = new HashSet<SysAuthoritiesResources>(
			0);

	// Constructors

	/** default constructor */
	public SysAuthorities() {
	}

	/** minimal constructor */
	public SysAuthorities(String authorityName) {
		this.authorityName = authorityName;
	}

	/** full constructor */
	public SysAuthorities(String authorityMark, String authorityName,
			String authorityDesc, String message, Integer enable,
			Integer issys, String moduleId,
			Set<SysRolesAuthorities> sysRolesAuthoritieses,
			Set<SysAuthoritiesResources> sysAuthoritiesResourceses) {
		this.authorityMark = authorityMark;
		this.authorityName = authorityName;
		this.authorityDesc = authorityDesc;
		this.message = message;
		this.enable = enable;
		this.issys = issys;
		this.moduleId = moduleId;
		this.sysRolesAuthoritieses = sysRolesAuthoritieses;
		this.sysAuthoritiesResourceses = sysAuthoritiesResourceses;
	}

	public String getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthorityMark() {
		return authorityMark;
	}

	public void setAuthorityMark(String authorityMark) {
		this.authorityMark = authorityMark;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getAuthorityDesc() {
		return authorityDesc;
	}

	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer getIssys() {
		return issys;
	}

	public void setIssys(Integer issys) {
		this.issys = issys;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public Set<SysRolesAuthorities> getSysRolesAuthoritieses() {
		return sysRolesAuthoritieses;
	}

	public void setSysRolesAuthoritieses(
			Set<SysRolesAuthorities> sysRolesAuthoritieses) {
		this.sysRolesAuthoritieses = sysRolesAuthoritieses;
	}

	public Set<SysAuthoritiesResources> getSysAuthoritiesResourceses() {
		return sysAuthoritiesResourceses;
	}

	public void setSysAuthoritiesResourceses(
			Set<SysAuthoritiesResources> sysAuthoritiesResourceses) {
		this.sysAuthoritiesResourceses = sysAuthoritiesResourceses;
	}
	
	
	
	
	
	
}