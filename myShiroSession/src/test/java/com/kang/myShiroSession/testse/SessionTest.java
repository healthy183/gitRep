package com.kang.myShiroSession.testse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

public class SessionTest extends BaseTest {

	@Test
	public void testGetSession() throws InterruptedException{
		
		login("classpath:shiro.ini", "zhang", "123");
		
		Subject subject  = SecurityUtils.getSubject();
		
		Session session  = subject.getSession();
		
		System.out.println("sessionId:"+session.getId());
		
		System.out.println("login userIP:"+session.getHost());
	
		System.out.println("session time:"+session.getTimeout());
		
		System.out.println("session create Time:"+session.getStartTimestamp());
		
		System.out.println("session last accessTime:"+session.getLastAccessTime());
	
		Thread.sleep(1000L);
	
		session.touch();//update last accessTime;
		
		System.out.println("session last accessTime:"+session.getLastAccessTime());
	
	
		session.setAttribute("key", "123");
		
		Assert.assertEquals("123", session.getAttribute("key"));
	
		session.removeAttribute("key");
		
		Assert.assertEquals(null, session.getAttribute("key"));
		
	}	
	
	
	
	
}
