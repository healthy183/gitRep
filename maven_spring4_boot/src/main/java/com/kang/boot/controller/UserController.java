package com.kang.boot.controller;

import javax.websocket.server.PathParam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kang.boot.model.User;

//@EnableAutoConfiguration
@RestController
@RequestMapping("/user")
public class UserController {

	
	//http://localhost:9090/user/1
	@RequestMapping("/{id}")  
	public User view(@PathVariable("id") Long id){
		
		User user = new User();
		user.setId(id);
		user.setName("Healthy梁健康");
		
		System.out.println(user.toString());
		
		return user;
	}
	
	
	//should add "@EnableAutoConfiguration" in once class run!
	/*
	public static void main(String[] args) {
		SpringApplication.run(UserController.class);  
		
	}*/
	
}
