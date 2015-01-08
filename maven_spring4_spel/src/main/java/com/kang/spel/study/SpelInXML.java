package com.kang.spel.study;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpelInXML {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		//String world = (String)app.getBean("world");
	
		String world = app.getBean("world",String.class);
		
		System.out.println(world);//world!
		
		
		world = app.getBean("world2",String.class);
		
		System.out.println(world);//Hello world!
		
		
		world = app.getBean("hello3",String.class);
		
		System.out.println(world);//Hello world!
		
		
		SpELBean bean  = app.getBean("helloBean1",SpELBean.class);
		System.out.println(bean.getValue());//Hello healthy!
	
		bean  = app.getBean("helloBean2",SpELBean.class);
		System.out.println(bean.getValue());//haha
	}

}
