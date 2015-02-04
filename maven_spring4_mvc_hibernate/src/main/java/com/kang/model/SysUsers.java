package com.kang.model;

// Generated 2013-11-27 14:50:48 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SysUsers generated by hbm2java
 */
@Entity
@Table(name = "sys_users", catalog = "spring3hibernate4")
public class SysUsers implements java.io.Serializable {

	private Integer usrid;
	private SysUsers sysUsers;
	private String usrname;
	private String usrpwd;
	private Integer usrtype;
	private Set<TReimapp> TReimapps = new HashSet<TReimapp>(0);
	private Set<SysUsers> sysUserses = new HashSet<SysUsers>(0);
	private Set<TReim> TReims = new HashSet<TReim>(0);

	public SysUsers() {
	}

	public SysUsers(SysUsers sysUsers, String usrname, String usrpwd,
			Integer usrtype, Set<TReimapp> TReimapps, Set<SysUsers> sysUserses, Set<TReim> TReims) {
		this.sysUsers = sysUsers;
		this.usrname = usrname;
		this.usrpwd = usrpwd;
		this.usrtype = usrtype;
		this.TReimapps = TReimapps;
		this.sysUserses = sysUserses;
		this.TReims = TReims;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "usrid", unique = true, nullable = false)
	public Integer getUsrid() {
		return this.usrid;
	}

	public void setUsrid(Integer usrid) {
		this.usrid = usrid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usrmgr")
	public SysUsers getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(SysUsers sysUsers) {
		this.sysUsers = sysUsers;
	}

	@Column(name = "usrname", length = 20)
	public String getUsrname() {
		return this.usrname;
	}

	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}

	@Column(name = "usrpwd", length = 20)
	public String getUsrpwd() {
		return this.usrpwd;
	}

	public void setUsrpwd(String usrpwd) {
		this.usrpwd = usrpwd;
	}

	@Column(name = "usrtype")
	public Integer getUsrtype() {
		return this.usrtype;
	}

	public void setUsrtype(Integer usrtype) {
		this.usrtype = usrtype;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysUsers")
	public Set<TReimapp> getTReimapps() {
		return this.TReimapps;
	}

	public void setTReimapps(Set<TReimapp> TReimapps) {
		this.TReimapps = TReimapps;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysUsers")
	public Set<SysUsers> getSysUserses() {
		return this.sysUserses;
	}

	public void setSysUserses(Set<SysUsers> sysUserses) {
		this.sysUserses = sysUserses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysUsers")
	public Set<TReim> getTReims() {
		return this.TReims;
	}

	public void setTReims(Set<TReim> TReims) {
		this.TReims = TReims;
	}

}