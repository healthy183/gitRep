package com.kang.validate.controller;

import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kang.validate.model.NotNullModel;


@Scope("prototype")
@Controller
@RequestMapping("/validate")
public class ValidateController {

	/**
	 * <form:input path="usrName"/>
	 * 	<!-- <form:form commandName="notNullModel" action="validate/NotNullModelValidate.html" method="post">	</form:form> -->
	 * 
	 * 
	 * */
	@RequestMapping(value="/NotNullModelValidate",method=RequestMethod.POST)
	public String NotNullModelValidate( @ModelAttribute("notNullModel")NotNullModel notNullModel){
		
		 org.springframework.web.context.ContextLoaderListener s;
		
		
		return "/jsp/good";
	}
	
	
	public static void main(String[] args) {
		
		
		String s = "YU88 -=;/?";
		System.out.println(s.length());
	}
	
	
}
