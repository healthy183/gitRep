package com.kang.propertyScript.module;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


/*@ScriptAssert(script="_this.password == _this.confirmation", lang = "javascript",
		alias="_this",message="{checkPassword.confirmation.unequals}")*/

@MyScriptAssert(script="_this.password == _this.confirmation", lang = "javascript",
alias="_this",  message="{checkPassword.confirmation.unequals}", 
property = "confirmation")
public class PropertyScriptModel implements java.io.Serializable {

	@NotBlank
	private String password;
	@NotEmpty
	private String confirmation;
	
	
	public PropertyScriptModel() {
		super();
	}

	public PropertyScriptModel(String password, String confirmation) {
		super();
		this.password = password;
		this.confirmation = confirmation;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}
	
}

