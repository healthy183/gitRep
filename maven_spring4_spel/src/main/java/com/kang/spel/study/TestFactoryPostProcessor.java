package com.kang.spel.study;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestFactoryPostProcessor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//自定义规则
		ApplicationContext app = new ClassPathXmlApplicationContext("SpELBeanFactoryPostProcessor.xml");
		String world = app.getBean("world",String.class);
		
		System.out.println(world);//world!
		
		
	}

}
