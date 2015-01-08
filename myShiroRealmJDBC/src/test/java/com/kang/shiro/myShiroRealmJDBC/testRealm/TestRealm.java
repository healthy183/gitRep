package com.kang.shiro.myShiroRealmJDBC.testRealm;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

public class TestRealm extends BaseTest {

	String classpatch = "classpath:shiro.ini";
	
	 //@Test
	public void testLogin(){ 
		 //test try login  over 5 times  throw ExcessiveAttemptsException;
		 /**
		 for(int i = 0;i<5;i++){
			 
			 try {
				 login("classpath:shiro.ini",u1.getUserName(),"abc"); 
			} catch (Exception e) {
				// TODO: handle exception
			}
		 }
		login("classpath:shiro.ini",u1.getUserName(),"");
		  */
		 
		login("classpath:shiro.ini",u1.getUserName(),password); 
		 
		Subject subject = super.subject();
		
		Assert.assertTrue(subject.isAuthenticated());
	}
	
     //@Test(expected = UnknownAccountException.class)
     //@Test
	 public void testUnknowAccount(){
		 
		 login(classpatch,u1.getUserName()+"KANG",password); 
		 
	 }
     
     //@Test
	 //@Test(expected = IncorrectCredentialsException.class)
     public  void errorPassword(){
    	 login(classpatch, u1.getUserName(), password+"1");
     }
	 
     
     //@Test
    // @Test(expected = LockedAccountException.class)
	 public void lockAccount(){
    	 login(classpatch, u4.getUserName(), password);
	 }
	
    
	// @Test
     public void hadRole(){
    	 
		 login(classpatch, u1.getUserName(), password);
		 
		 boolean hadRole = subject().hasRole("admin");
		 
		 Assert.assertTrue(hadRole);
		 
		 hadRole = subject().hasRole("user");
		 
		 Assert.assertFalse(hadRole);
     }
	
     
    // @Test
	 public void hadPermission(){
		
		 login(classpatch, u1.getUserName(), password);
		 
		 boolean hadPermitted = subject().isPermitted("user:create");
		 
		 Assert.assertTrue(hadPermitted);
		 
		  hadPermitted = subject().isPermitted("user:delete");
		 
		  Assert.assertFalse(hadPermitted);
		 
	 }
	
	
     @Test
	 public void hadPermissionAll(){
    	 
    	 login(classpatch, u1.getUserName(), password);
    	 
    	 boolean hadPermitted = subject().isPermittedAll("user:create","user:update");
    	 
    	 Assert.assertTrue(hadPermitted);
    	 
    	 hadPermitted = subject().isPermittedAll("user:create","user:delete");
    	 
    	 Assert.assertFalse(hadPermitted);
     }
	
	
	
}
