package com.kang.validate.controller;

import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kang.validate.model.NotNullModel;

@Controller
@Scope("prototype")
public class ValidateController {

	
	
	@RequestMapping("/validate/notNull")
	public String notNull(@Valid @ModelAttribute("model")NotNullModel notNullModel, Errors errors){
	
		if(errors.hasErrors()){
			return "valid/error";
		}
		
		return "bind/success";
	}
	
	
	@RequestMapping("/validate/notNulls")
	public String notNullss(){
	
		/*if(errors.hasErrors()){
			return "valid/error";
		}*/
		
		return "bind/success";
	}
	
	
	
}
