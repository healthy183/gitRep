package com.kang.validate.model;

public class PhoneNumberModel {

	private String areaCode;	
	private String phoneNumber;
	
	

	public PhoneNumberModel() {
		super();
	}

	
	public PhoneNumberModel(String areaCode, String phoneNumber) {
		super();
		this.areaCode = areaCode;
		this.phoneNumber = phoneNumber;
	}


	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((areaCode == null) ? 0 : areaCode.hashCode());
		result = prime * result
				+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		return result;
	}

	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhoneNumberModel other = (PhoneNumberModel) obj;
		if (areaCode == null) {
			if (other.areaCode != null)
				return false;
		} else if (!areaCode.equals(other.areaCode))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "PhoneNumberModel [areaCode=" + areaCode + ", phoneNumber="
				+ phoneNumber + "]";
	}
	
}
