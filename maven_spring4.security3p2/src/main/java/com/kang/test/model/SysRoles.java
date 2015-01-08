package com.kang.test.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


public class SysRoles implements java.io.Serializable {
	
	private String roleId;
	private String roleName;
	private String roleDesc;
	private Integer enable;
	private Integer issys;
	private String moduleId;
	private Set<SysRolesMoudles> sysRolesMoudleses = new HashSet<SysRolesMoudles>(
			0);
	private Set<SysUsersRoles> sysUsersRoleses = new HashSet<SysUsersRoles>(0);
	private Set<SysRolesAuthorities> sysRolesAuthoritieses = new HashSet<SysRolesAuthorities>(
			0);

	// Constructors

	/** default constructor */
	public SysRoles() {
	}

	/** full constructor */
	public SysRoles(String roleName, String roleDesc, Integer enable,
			Integer issys, String moduleId,
			Set<SysRolesMoudles> sysRolesMoudleses,
			Set<SysUsersRoles> sysUsersRoleses,
			Set<SysRolesAuthorities> sysRolesAuthoritieses) {
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.enable = enable;
		this.issys = issys;
		this.moduleId = moduleId;
		this.sysRolesMoudleses = sysRolesMoudleses;
		this.sysUsersRoleses = sysUsersRoleses;
		this.sysRolesAuthoritieses = sysRolesAuthoritieses;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
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

	public Set<SysRolesMoudles> getSysRolesMoudleses() {
		return sysRolesMoudleses;
	}

	public void setSysRolesMoudleses(Set<SysRolesMoudles> sysRolesMoudleses) {
		this.sysRolesMoudleses = sysRolesMoudleses;
	}

	public Set<SysUsersRoles> getSysUsersRoleses() {
		return sysUsersRoleses;
	}

	public void setSysUsersRoleses(Set<SysUsersRoles> sysUsersRoleses) {
		this.sysUsersRoleses = sysUsersRoleses;
	}

	public Set<SysRolesAuthorities> getSysRolesAuthoritieses() {
		return sysRolesAuthoritieses;
	}

	public void setSysRolesAuthoritieses(
			Set<SysRolesAuthorities> sysRolesAuthoritieses) {
		this.sysRolesAuthoritieses = sysRolesAuthoritieses;
	}
	
	
	
}