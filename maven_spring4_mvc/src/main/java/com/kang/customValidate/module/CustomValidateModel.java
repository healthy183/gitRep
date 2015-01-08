package com.kang.customValidate.module;



@CheckPassword()  
public class CustomValidateModel {
	
	private String password;
	
	private String confirmation;

	
	
	public CustomValidateModel() {
		super();
	}

	public CustomValidateModel(String password, String confirmation) {
		super();
		this.password = password;
		this.confirmation = confirmation;
	}

	
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	public String getPassword() {
		return password;
	}

	public String getConfirmation() {
		return confirmation;
	}
}
