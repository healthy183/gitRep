package com.kang.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kang.test.model.SysUsers;
import com.kang.test.service.ItestService;


@Controller
@RequestMapping(value="/test")
public class TestController {

	@Autowired
	private ItestService itestService;
	
	
	@RequestMapping(value="/testJump", method=RequestMethod.GET)
	public String testJump(){
			
		
		List<SysUsers> user = itestService.findAlluser();
		
		
		
		System.out.println("jump!");
		
		return "test/jump";
	}

	
	
	

}
