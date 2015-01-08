package com.kang.newFour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kang.newThreePointOne.model.NewUser;

@Controller
@RequestMapping("/testRestController")
public class TestRestController {

	
	
	@RequestMapping(value="/testRest",method=RequestMethod.POST)
	public @ResponseBody NewUser testRest(){
		//org.springframework.http.converter.json.MappingJacksonHttpMessageConverter c;
		
		NewUser  user = new NewUser();
		user.setUsrId(123);
		System.out.println("s");
		user.setUsrName("restName");
		user.setUsrSwd("restSwd");
		return user;
	}
	
	
}
