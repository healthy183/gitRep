package com.kang.newThreePointOne.controller;

import org.jboss.logging.Param;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kang.newThreePointOne.model.MyArgumentUser;



@Controller
@Scope("prototype")
@RequestMapping("/customerWebArgumentHandler")
public class MyCustomerWebArgumentHandlerController {

	
	@RequestMapping(value="/webArgumentHandler",method=RequestMethod.POST)
	public String webArgumentHandler(@Param @ModelAttribute("usr")MyArgumentUser myArgumentUser){
		return "customerWebArgumentHandler/webArgumentHandler";
	}
	
	
}
