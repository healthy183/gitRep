package com.kang.spring.mvc;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AnnotationController {

	
	
	@RequestMapping("/helloOne")
	public String helloOne(){
		
		SecurityUtils.getSubject().checkRole("admin");
		
		
		System.out.println("wfh");
		
		return "success";
	}
	
	
	@RequiresRoles("admin")
	@RequestMapping("/helloTwo")
	public String helloTwo(){
		
		System.out.println("wfh");
		
		System.out.println("cjb");
		
		return "success";
	}
	

}
