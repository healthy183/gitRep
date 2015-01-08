package com.kang.model.test;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_user", catalog = "")
public class TdUser implements Serializable {

		
	private Integer id;
	private String  userName;
	private String password;
	private String number;
	private String flag;
	
	private TbUserDetail tbUserDetail;
	
	@OneToOne
	@JoinColumn(name = "id")
	public TbUserDetail getTbUserDetail() {
		return tbUserDetail;
	}

	public void setTbUserDetail(TbUserDetail tbUserDetail) {
		this.tbUserDetail = tbUserDetail;
	}

	public TdUser() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "userName", length = 50)
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", length = 20, nullable = false)
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "number", length = 2)
	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "flag", length = 1)
	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}

	
	
}
