package com.kang.validate.controller;

import javax.validation.groups.ConvertGroup;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kang.user.groupValidateInterface.First;
import com.kang.user.groupValidateInterface.Second;
import com.kang.user.model.GroupValidateModel;

@Scope("prototype")
@Controller
@RequestMapping("/groupValidate")
public class GroupsValidateController {

	/**
	 * error link:
	 http://localhost:9080/maven_spring4_mvc/groupValidate/groupsFirstValidate.html?name=kang
	 
	 * success link:
	 http://localhost:9080/maven_spring4_mvc/groupValidate/groupsFirstValidate.html?name=kang111&id=123&password=abc
	 * */
	@RequestMapping("/groupsFirstValidate")
	public String groupsFirstValidate(@Validated({First.class})@ModelAttribute("groupValidatemodel") GroupValidateModel groupValidatemodel,
			BindingResult result){
		
		if(result.hasErrors()){
			return "error/groupsFirstValidate";
		}
		return "welcome/welcome";
	}
	
	/**
	 * error link:
	 http://localhost:9080/maven_spring4_mvc/groupValidate/groupsSecondValidate.html?name=123aaaaaaa
	 
	 * success link:
	http://localhost:9080/maven_spring4_mvc/groupValidate/groupsSecondValidate.html?name=aaaaaaaaa&id=1111&password=abc
	 * */
	@RequestMapping("/groupsSecondValidate")
	public String groupsSecondValidate(@Validated({Second.class})@ModelAttribute("groupValidatemodel") GroupValidateModel groupValidatemodel ,
			BindingResult result){
		
		if(result.hasErrors()){
			return "error/groupsSecondValidate";
		}
		return "welcome/welcome";
	}
	
	/**
	 * error link:
	 http://localhost:9080/maven_spring4_mvc/groupValidate/groupsAllValidate.html?name=1
	 
	 * success link:
	http://localhost:9080/maven_spring4_mvc/groupValidate/groupsAllValidate.html?name=aaaaaaaaa&id=1111&password=abc
	 * */
	@RequestMapping("/groupsAllValidate")//
	public String groupsAllValidate(@Validated({Second.class,First.class})@ModelAttribute("groupValidatemodel") GroupValidateModel groupValidatemodel ,
			BindingResult result){
		/**
		 * if the field had  stated all the group then it will  be validated all without order 
		 * but add the attribute @GroupSequence;
		 * */
		if(result.hasErrors()){
			return "error/groupsSecondValidate";
		}
		return "welcome/welcome";
	}
	
	
	//fail example
	/**
	 * error link:
	 http://localhost:9080/maven_spring4_mvc/groupValidate/groupsConvertValidate.html?name=1&son.sonId=1ssss
	 
	 * success link:
	http://localhost:9080/maven_spring4_mvc/groupValidate/groupsConvertValidate.html?name=aaaaaaaaa&id=1111&password=abc
	 * */
	@RequestMapping("/groupsConvertValidate")//
	public String groupsConvertValidate(@Validated({Second.class,First.class})@ModelAttribute("groupValidatemodel")
		GroupValidateModel groupValidatemodel ,
			BindingResult result){
		/**
		 * stated the @ConvertGroup for  cascaded valdiate 
		 * */
		if(result.hasErrors()){
			return "error/groupsConvertValidate";
		}
		return "welcome/welcome";
	}
	
	
}
