package com.kang.shiro.myShiroRealmJDBC.testRealm;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import com.kang.shiro.myShiroRealmJDBC.model.UserModel;

public class PrincialCollectionTest  extends BaseTest {

	
	
	@Test
	public void test(){
		
		login("classpath:shiro-multirealm.ini", "abc", "123");
	
		Subject subject = subject();
		//get Primary Principal(the first Principal)
		Object primaryPrincipal = subject.getPrincipal();
		
		PrincipalCollection principalCollection = subject.getPrincipals();
	
		Object primaryPrincipalTwo = principalCollection.getPrimaryPrincipal();
	
		Assert.assertEquals(primaryPrincipal,primaryPrincipalTwo);
		
		Set<String> setStr = principalCollection.getRealmNames();
		//[a, b, c]
		System.out.println(setStr.toString());
		
		//[zhang, User{id=null, username='zhang', password='123', salt='null', locked=false}]
		setStr =  principalCollection.asSet();
		System.out.println(setStr.toString());
		
		List<String> listStr =  principalCollection.asList();
		System.out.println(listStr.toString());//the same result as asSet
		
		//zhang
		Collection<UserModel>  userCollection = principalCollection.fromRealm("a");
		System.out.println(userCollection.toString());
		
		
	}
	
	
	
	
}
