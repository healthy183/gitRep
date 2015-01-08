package com.kang.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Scope("prototype")
@Controller
@RequestMapping("/main")
public class MainController {

	@RequestMapping(value="/lgn",method=RequestMethod.POST)
	public ModelAndView lgn(HttpServletRequest request){
		
		
		String usrname = request.getParameter("usrname");
		String usrpwd = request.getParameter("usrpwd");

		
		request.setAttribute("usrname", usrname);
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("usrpwd", usrpwd);	
		mv.setViewName("welcome/welcome");
		
		return mv;
	}
	
	
	
}
