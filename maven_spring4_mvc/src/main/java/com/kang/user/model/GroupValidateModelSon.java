package com.kang.user.model;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.kang.user.groupValidateInterface.First;
import com.kang.user.groupValidateInterface.Second;

public class GroupValidateModelSon implements Serializable {

	@Length(max = 2,groups = {First.class})
	@Pattern(regexp = "[a-zA-Z]{5,20}", groups = {Second.class})  
	private String sonId;

	public GroupValidateModelSon(String sonId) {
		super();
		this.sonId = sonId;
	}

	public String getSonId() {
		return sonId;
	}

	public void setSonId(String sonId) {
		this.sonId = sonId;
	}
	
}
