package com.kang.shiro.myShiroAuthorization;

import java.util.Arrays;
import java.util.List;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

public class RoleTest extends BaseTest {

	
	//@Test
	public void mainTest(){
		
		login("shiro-role.ini", "zhang", "123");
		
		Subject subject = getSubject();
		
		Assert.assertTrue(subject.hasRole("role1"));
		
		List<String> list = Arrays.asList("role1","role2");
		
		Assert.assertTrue(subject.hasAllRoles(list));
		
		list = Arrays.asList("role1","role2","roleA");
		
		boolean[] booleanArray = subject.hasRoles(list);
		
		
		Assert.assertTrue(booleanArray[0]);
		
		Assert.assertTrue(booleanArray[1]);
		
		Assert.assertTrue(booleanArray[2]);
		
		System.out.println("test successfully!");
		 
		
	}
	
	//@Test(expected = UnauthorizedException.class)
	public void testCheckRole(){
		
		login("shiro-role.ini", "zhang", "123");
		
		Subject subject = getSubject();
		
		subject.checkRole("role1");
		// throw exception if no role3
		subject.checkRoles("role1","role3");
		
	}
	
	
}
