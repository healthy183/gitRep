package com.kang.validate.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kang.newFour.service.NewFourService;
import com.kang.newThreePointOne.model.IocNewUser;
import com.kang.user.model.ValidateNotNull;
import com.kang.user.model.ValidateNotNull2;
import com.kang.user.service.ImainService;


@Scope("prototype")
@Controller
@RequestMapping("/main")
public class MainController {

	/**/
	@Autowired(required=true)
	@Qualifier("newFourServiceImpl")
	private NewFourService newFourServiceImpl;
	@Autowired
	@Qualifier("imainService")
	private ImainService imainService;
	
	@RequestMapping(value="/lgn",method=RequestMethod.POST)
	public ModelAndView lgn(HttpServletRequest request){
		
		
		newFourServiceImpl.save(new IocNewUser());
		
		
		imainService.lgnin();
		
		String usrname = request.getParameter("usrname");
		String usrpwd = request.getParameter("usrpwd");

		
		request.setAttribute("usrname", usrname);
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("usrpwd", usrpwd+"add by healthy!");	
		mv.setViewName("welcome/welcome");
		
		return mv;
	}
	
	@RequestMapping(value="/validateNotNull",method=RequestMethod.POST)
	public String validateNotNull(@Valid @ModelAttribute("user")ValidateNotNull validateNotNull,Errors errors){
		
		System.out.println("s");
		System.out.println("?ss");
		
		if(errors.hasErrors()){
			return "error/notNullError";
		}
		
		return "welcome/welcome";
	}
	
	
	@RequestMapping(value="/validateDoubleNotNull",method=RequestMethod.POST)
	public String validateDoubleNotNull(@Valid @ModelAttribute("user")ValidateNotNull validateNotNull,Errors errors,
			@Valid @ModelAttribute("user2")ValidateNotNull2 validateNotNull2,Errors errors2){
		
		
		if(errors.hasErrors()){
			return "error/validateDoubleNotNull";
		}
		
		if(errors2.hasErrors()){
			return "error/validateDoubleNotNull";
		}
		
		return "welcome/welcome";
	}
	
	
	//   http://localhost:9080/maven_spring4_mvc/main/changePassword.html?password=1&confirmation=12
	 
	//fail test!
	
	@RequestMapping("/changePassword")  
	public String changePassword( @RequestParam("password") String password,
			@RequestParam("confirmation") String confirmation, Model model){
		
		try {
			imainService.changePassword(password, confirmation);  
		} catch (ConstraintViolationException e) {
			for(ConstraintViolation violation : e.getConstraintViolations()) {  
	            System.out.println(violation.getMessage());  
	        } 
		}
		
		
		
		return "welcome/welcome";
		
		
	}
	
	
}
