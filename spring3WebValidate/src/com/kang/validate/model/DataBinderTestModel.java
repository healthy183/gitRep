package com.kang.validate.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class DataBinderTestModel {

	private String username;
	private boolean bool;// test boolean
	private SchoolInfoModel schooInfo;// test model
	private List hobbyList;// test list array to set or set to array
	private Map map;// test map
	private PhoneNumberModel phoneNumber;// test String to model or model to
											// String
	private Date date;// test date
	private UserState state;// String to enum;

	@Override
	public String toString() {
		return "DataBinderTestModel [username=" + username + ", bool=" + bool
				+ ", schooInfo=" + schooInfo + ", hobbyList=" + hobbyList
				+ ", map=" + map + ", phoneNumber=" + phoneNumber + ", date="
				+ date + ", state=" + state + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}

	public SchoolInfoModel getSchooInfo() {
		return schooInfo;
	}

	public void setSchooInfo(SchoolInfoModel schooInfo) {
		this.schooInfo = schooInfo;
	}

	public List getHobbyList() {
		return hobbyList;
	}

	public void setHobbyList(List hobbyList) {
		this.hobbyList = hobbyList;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public PhoneNumberModel getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(PhoneNumberModel phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UserState getState() {
		return state;
	}

	public void setState(UserState state) {
		this.state = state;
	}

}
