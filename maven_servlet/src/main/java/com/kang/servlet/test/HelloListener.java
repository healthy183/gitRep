package com.kang.servlet.test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class HelloListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletcontextevent) {
		System.out.println("lister destory!");
	}

	public void contextInitialized(ServletContextEvent servletcontextevent) {
		
		System.out.println("listener starting!");
	}

}
