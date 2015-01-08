package com.kang.validate.model;

import javax.validation.constraints.NotNull;

public class NotNullModel {
	
	@NotNull
	public String usrName;

	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	
	
}
