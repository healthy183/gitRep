package com.kang.user.model;

import org.hibernate.validator.constraints.NotBlank;

public class ValidateNotNull2 {

	@NotBlank
	public String usrGname;

	public String getUsrGname() {
		return usrGname;
	}

	public void setUsrGname(String usrGname) {
		this.usrGname = usrGname;
	}

	
	
}

