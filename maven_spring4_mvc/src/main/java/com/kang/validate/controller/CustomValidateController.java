package com.kang.validate.controller;

import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kang.customValidate.module.CustomValidateModel;
import com.kang.propertyScript.module.PropertyScriptModel;

@Scope("prototype")
@Controller
@RequestMapping("/customValidate")
public class CustomValidateController {

	
	/**
	 * error link:
	   http://localhost:9080/maven_spring4_mvc/customValidate/testConfirmPassword.html
	 
	   http://localhost:9080/maven_spring4_mvc/customValidate/testConfirmPassword.html?password=1
	 	
	   http://localhost:9080/maven_spring4_mvc/customValidate/testConfirmPassword.html?password=1&confirmation=12
	 
	 * success link:
	 http://localhost:9080/maven_spring4_mvc/customValidate/testConfirmPassword.html?password=1&confirmation=1

	 
	 * */
	@RequestMapping("/testConfirmPassword")
	public String testConfirmPassword(@Valid @ModelAttribute("customValidateModel")
		CustomValidateModel customValidateModel,BindingResult bindingResult){
		
		if(bindingResult.hasErrors()){
			return "error/testConfirmPassword";
		}
		
		return "welcome/welcome";
	}
	
	/**
	 * error link:
	   http://localhost:9080/maven_spring4_mvc/customValidate/testConfirmPasswordByScript.html
	 
	   http://localhost:9080/maven_spring4_mvc/customValidate/testConfirmPasswordByScript.html?password=1
	 	
	   http://localhost:9080/maven_spring4_mvc/customValidate/testConfirmPasswordByScript.html?password=1&confirmation=12
	 
	 * success link:
	 http://localhost:9080/maven_spring4_mvc/customValidate/testConfirmPasswordByScript.html?password=1&confirmation=1

	 
	 * */
	@RequestMapping("/testConfirmPasswordByScript")
	public String testConfirmPasswordByScript(@Valid @ModelAttribute("customValidateModel")
	PropertyScriptModel propertyScriptModel,BindingResult bindingResult){
		//testScriptConfirmPassword
		System.out.println("as");
		//field
		if(bindingResult.hasErrors()){
			return "error/testScriptConfirmPassword";
		}
		
		return "welcome/welcome";
	}
	
	
	
	
}
