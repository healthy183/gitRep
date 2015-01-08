package com.kang.user.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kang.user.service.ImainService;

public class MainServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BeanFactory  factory = new ClassPathXmlApplicationContext("applicationContext.xml");  
		ImainService imainService = (ImainService) factory.getBean("imainService");  
		
		String s = imainService.lgnin();
		System.out.println(s);
	}

}
