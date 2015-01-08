package com.kang.newThreePointTwo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;

import com.kang.newThreePointOne.model.IocNewUser;


@ControllerAdvice
public class ControllerAdviceTest {

	@ModelAttribute
	public IocNewUser testModel(){
		
		System.out.println("@ModelAttribute:调用@requestMapping的所有方法！");
		
		IocNewUser newUser = new IocNewUser();
		newUser.setUsrName("kds");
		return newUser;
	}
	
	@InitBinder 
	public void testInitBinder(){
		System.out.println("@InitBinder:调用@requestMapping的所有方法！");
	}
	
	
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String testExceptionHandler(NativeWebRequest request, NullPointerException e){
		
		System.out.println("call it when throws NullPointerException");
		
		
		return "newAttributeattributeInThreePointTwo/NullPointerExceptionPage"; //返回一个逻辑视图名  
	}
	
	
	
	
	
}
