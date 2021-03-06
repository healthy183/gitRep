package com.mavenHiberate4.model;

// Generated 2013-11-27 14:50:48 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TReimapp generated by hbm2java
 */
@Entity
@Table(name = "t_reimapp", catalog = "spring3hibernate4")
public class TReimapp implements java.io.Serializable {

	private Integer appId;
	private TReim TReim;
	private SysUsers sysUsers;
	private String apptext;
	private String appdesc;
	private Date appdate;

	public TReimapp() {
	}

	public TReimapp(TReim TReim, SysUsers sysUsers, String apptext,
			String appdesc, Date appdate) {
		this.TReim = TReim;
		this.sysUsers = sysUsers;
		this.apptext = apptext;
		this.appdesc = appdesc;
		this.appdate = appdate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "app_id", unique = true, nullable = false)
	public Integer getAppId() {
		return this.appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rmid")
	public TReim getTReim() {
		return this.TReim;
	}

	public void setTReim(TReim TReim) {
		this.TReim = TReim;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usrid")
	public SysUsers getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(SysUsers sysUsers) {
		this.sysUsers = sysUsers;
	}

	@Column(name = "apptext", length = 30)
	public String getApptext() {
		return this.apptext;
	}

	public void setApptext(String apptext) {
		this.apptext = apptext;
	}

	@Column(name = "appdesc", length = 100)
	public String getAppdesc() {
		return this.appdesc;
	}

	public void setAppdesc(String appdesc) {
		this.appdesc = appdesc;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "appdate", length = 19)
	public Date getAppdate() {
		return this.appdate;
	}

	public void setAppdate(Date appdate) {
		this.appdate = appdate;
	}

}
