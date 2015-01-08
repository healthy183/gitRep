package com.kang.newThreePointOne.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kang.newThreePointOne.model.IocNewUser;

@Scope("prototype")
@Controller
@RequestMapping("/newAttributeattributeInThreePointOne")
public class NewAttributeattributeController {

	
	@RequestMapping(value="/consumesAndproduces", //headers="",
			method=RequestMethod.POST, //consumes produces are the new attriubute in 3.1
			//it means the Content-Type(enctype) of  request header  is application/x-www-form-urlencoded
				consumes="application/x-www-form-urlencoded", 
					produces="text/html")//the Accept of  request header is  text/html 
	public ModelAndView  consumesAndproduces(){
		
		ModelAndView model  = new ModelAndView();
		
		model.addObject("obj", "hello word");
		
		model.setViewName("newAttributeattributeInThreePointOne/consumesAndproduces");
		
		return model;
		
	}
	
	@RequestMapping(value="/{newAttrValue}")
	public String newAttrValue(@PathVariable String newAttrValue){
		
		/** no need since 3.1
		 * 
		 * Model model = new Model();
		 * model.setObject("newAttrValue",newAttrValue);
		 * */
		
		/** no need since 3.1
		 * 
		 * redirect:/newAttr.html
		 * */
		return "redirect:/{newAttrValue}.html";
	}
	
	@RequestMapping(value="/newAttr")
	public String newAttr(){
		return "newAttributeattributeInThreePointOne/newAttr";
	}
	
	@RequestMapping(value="/newMoble/{usrName}")
	/**no need since 3.1
	 * 
	 * 	public String newMoble(NewUser newUser, @PathVariable String usrName){
	 * 		newUser.setUsrName(usrName);
	 * */
	public String newMoble(IocNewUser newUser){
		return "newAttributeattributeInThreePointOne/newUser";
	}
	/*
	@RequestMapping(value="/testAjaxValid",method=RequestMethod.POST)
	public String testAjaxValid(@Valid NewUser newUser){
		return "";
	}*/
	
}
