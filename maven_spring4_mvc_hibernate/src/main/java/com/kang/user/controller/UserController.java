package com.kang.user.controller;



import java.util.List;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kang.model.SysUsers;
import com.kang.model.vo.SysUsersVo;
import com.kang.user.service.IuserService;


@Scope("prototype")
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	@Qualifier("userServiceImpl")
	private IuserService userServiceImpl;
	
	
	@RequestMapping("/findAllUser")
	public ModelAndView findAllUser(HttpServletRequest request){
		/**/
		ModelAndView modelAndView  = new ModelAndView();
		 List<SysUsers> userList = userServiceImpl.findAll();
		 request.setAttribute("userList",userList); 
		 //modelAndView.addObject("userList",userList);
		 modelAndView.setViewName("user/findAllUser");
		 
		return modelAndView;
	}
	//post创建user
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public @ResponseBody Integer addUser
		(@RequestParam(value="usrName",required=true)String usrName,
			@RequestParam(value="usrPwd",required=true)String usrPwd){
		return userServiceImpl.addUser(usrName,usrPwd);
	}
	
	@RequestMapping(value="/updtusr/{userId}/{updtusrName}/{updtusrPwd}",method=RequestMethod.POST)
	public @ResponseBody void updtusr(@PathVariable("userId") String userId,
		@PathVariable("updtusrName") String updtusrName,
			@PathVariable("updtusrPwd") String updtusrPwd){
		//更新用户信息
		userServiceImpl.updtusr(userId,updtusrName,updtusrPwd);
		
	}
	
	@RequestMapping(value="delusr/{userId}",method=RequestMethod.POST)
	public @ResponseBody void delusr(@PathVariable("userId") String userId){
		//删除用户
		userServiceImpl.delusr(userId);
	}
	
	@RequestMapping(value="/findAllUserByPro")
	public String findAllUserByPro(HttpServletRequest request){
		//使用存储过程查询
		List<SysUsersVo> userList = userServiceImpl.findAllUserByPro();
		request.setAttribute("userList",userList);
		
		return "user/findAllUserByPro";
	}
	
}
