package com.kang.user.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kang.model.test.TdUser;
import com.kang.user.service.ITDuserService;

@Scope("prototype")
@Controller
@RequestMapping("/interview")
public class InterviewController {

	
	@Autowired
	private ITDuserService userServiceImpl;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String  login(TdUser user,Model model){
		
		
		System.out.println(user.getUserName());
		
		String msg = userServiceImpl.login(user);
		
		model.addAttribute("msg", msg);
		
		if(StringUtils.equals(msg, "success")){
			
			List<TdUser> userList  = userServiceImpl.findAll();
			model.addAttribute("userList", userList);
			
			//TdUser users = userList.get(0);
			
			//users.getTbUserDetail().getId();
			
			return "/interview/login";
		};
		
		return "/interview/index";
	}

	
	
}
