package com.kang.user.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class ValidateNotNull {

	@NotNull(message="{username.not.empty}")
	private String usrName;

	@NotNull(message="不能为空!")
    @Length(max=6,min=2,message="{user.name.length.error}")
	private String usrDname;
	
	@NotNull(message="{messageCodeResolver.not.empty}")
	private String messageCodeResolver;
	
	public String getMessageCodeResolver() {
		return messageCodeResolver;
	}

	public void setMessageCodeResolver(String messageCodeResolver) {
		this.messageCodeResolver = messageCodeResolver;
	}

	public String getUsrDname() {
		return usrDname;
	}

	public void setUsrDname(String usrDname) {
		this.usrDname = usrDname;
	}

	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	
	
}
