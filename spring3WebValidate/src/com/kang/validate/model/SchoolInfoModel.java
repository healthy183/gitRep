package com.kang.validate.model;

public class SchoolInfoModel {

	
	private String schoolType;
	private String schoolName;
	private String specialty;
	
	public SchoolInfoModel() {
		super();
	}

	public SchoolInfoModel(String schoolType, String schoolName,
			String specialty) {
		super();
		this.schoolType = schoolType;
		this.schoolName = schoolName;
		this.specialty = specialty;
	}

	public String getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	
	@Override
	public String toString() {
		return "SchoolInfoModel [schoolType=" + schoolType + ", schoolName="
				+ schoolName + ", specialty=" + specialty + "]";
	}
	
	
	
	
}
