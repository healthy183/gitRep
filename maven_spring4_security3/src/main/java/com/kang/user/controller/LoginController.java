package com.kang.user.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

	
	@RequestMapping("/login")
	public String login() {
		
		UserDetails u;
		
		System.out.println("???");
		
		
		return "login";
	}
	


}
