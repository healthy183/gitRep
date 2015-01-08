package com.kang.user.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.kang.supportedValidationTarget.module.CrossParameter;
import com.kang.user.service.ImainService;

@Validated
@Order(value=2)
@Scope("prototype")
@Service("imainService")
@Transactional
public class MainServiceImpl implements ImainService {

	
	public String lgnin(){
		System.out.println("this is spring4 imainService lgnin!");
		return "main";
	};
	
	 @CrossParameter  
	 public void changePassword(String password, String confirmation) { 
		 System.out.println("FUCK!");
	 }
	
	
	
	
}
