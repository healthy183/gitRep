package com.kang.newThreePointOne.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kang.newThreePointOne.model.IocNewUser;

@Scope("prototype")
@Controller
@RequestMapping("/handlerController")
public class HandlerMethodReturnValueHandlerController {

	
	@RequestMapping("/testNewUser")
	public IocNewUser testNewUser(){
	
		IocNewUser usr = new IocNewUser();
		usr.setUsrName("梁健康");
		usr.setUsrSwd("Swd@1234");
		return usr;
	}
	
	
	
}
