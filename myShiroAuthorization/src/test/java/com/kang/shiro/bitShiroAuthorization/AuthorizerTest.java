package com.kang.shiro.bitShiroAuthorization;

import org.junit.Assert;
import org.junit.Test;

import com.kang.shiro.myShiroAuthorization.BaseTest;

public class AuthorizerTest extends BaseTest {

	@Test
	public void testIsPermitted(){
		
		/*login("shiro-authorizer.ini", "zhang", "123");
		
		Assert.assertTrue(getSubject().isPermitted("+user1+10"));
		Assert.assertTrue(getSubject().isPermitted("menu:xing"));
		*/
		
		login("shiro-jdbc-authorizer.ini", "zhang", "123");
		
		Assert.assertTrue(getSubject().isPermitted("menu:xing"));
		
		Assert.assertTrue(getSubject().isPermitted("user3:dd"));
		
	}
	
	
	
	
	
	
}
