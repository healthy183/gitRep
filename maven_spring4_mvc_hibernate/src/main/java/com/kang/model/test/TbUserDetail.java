package com.kang.model.test;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tb_user_detail", catalog = "")
public class TbUserDetail implements Serializable {

	private int id;
	private String  userName;
	private String  age;
	private String  sex;
	private String  phone;
	private String  addDate;
	private String  success;
	
	
	public TbUserDetail() {
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "userName", length = 50)
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	@Column(name = "age", length = 20, nullable = false)
	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}

	@Column(name = "sex", length = 20, nullable = false)
	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "phone", length = 20, nullable = false)
	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "addDate", length = 20, nullable = false)
	public String getAddDate() {
		return addDate;
	}


	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	@Column(name = "success", length = 20, nullable = false)
	public String getSuccess() {
		return success;
	}


	public void setSuccess(String success) {
		this.success = success;
	}
	
	
	

}
