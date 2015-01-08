package com.kang.user.model;

import java.io.Serializable;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.groups.ConvertGroup;

import org.hibernate.validator.constraints.Length;

import com.kang.user.groupValidateInterface.First;
import com.kang.user.groupValidateInterface.Second;

@GroupSequence({First.class,Second.class,GroupValidateModel.class})
public class GroupValidateModel  implements Serializable{

	@NotNull(message = "{user.id.null}", groups = {First.class}) 
	private Long id;
	
	@Length(min = 5, max = 20, message = "{user.name.length.illegal}", groups = {First.class})  
	@Pattern(regexp = "[a-zA-Z]{5,20}", message = "{user.name.illegal}", groups = {Second.class})  
	private String name;  

	@NotNull(message = "{user.password.null}", groups = {First.class, Second.class}) 
	private String password;

	/*@Valid
	@ConvertGroup(from=First.class,to=Second.class)
	private GroupValidateModelSon son;
	
	public GroupValidateModel(Long id, String name, String password,
			GroupValidateModelSon son) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.son = son;
	}

	public GroupValidateModelSon getSon() {
		return son;
	}

	public void setSon(GroupValidateModelSon son) {
		this.son = son;
	}*/
	
	public GroupValidateModel() {
		super();
	}

	public GroupValidateModel(Long id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}  
	
}
