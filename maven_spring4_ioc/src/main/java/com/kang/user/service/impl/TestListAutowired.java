package com.kang.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.kang.user.service.ImainService;


@Component
public class TestListAutowired {
	
	@Autowired
	private List<ImainService> mainServicelist;

	public List<ImainService> getMainServicelist() {
		return mainServicelist;
	}

	
	public static void main(String[] args) {
		
		BeanFactory  factory = new ClassPathXmlApplicationContext("applicationContext.xml");  
		TestListAutowired testListAutowired = (TestListAutowired) factory.getBean("testListAutowired");  
		
		
		//the @order can control the autowired in turn
		List<ImainService> mainServicelist = testListAutowired.getMainServicelist();
		
		for(ImainService imainService : mainServicelist){
			System.out.println("test:"+imainService.lgnin());
			
		}
		 
		
	}
	
	
	
	
	
	

}
