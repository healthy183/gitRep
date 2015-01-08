package com.kang.shiro.loginAndOut;

import java.util.List;

import junit.framework.Assert;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;

import com.kang.shiro.authenticatorStrategy.OnlyOneAuthenticatorStrategy;

public class ShiroLoginAndOutTest {

	Subject subject = null;
	
	public void commonSecurity(String classPathVal,String usrName){
		

		//1,new the securityManagerFactory through ini;
		Factory<org.apache.shiro.mgt.SecurityManager> facotry =  null;
		
		//facotry = new IniSecurityManagerFactory("classpath:ShiroLoginAndOutTest.ini");
		facotry = new IniSecurityManagerFactory("classpath:"+classPathVal);

		//2,get the securityFactory and bound it;
		org.apache.shiro.mgt.SecurityManager securityManager 
				= facotry.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        
        //get the subject and validated you usrName and psw;
        subject =  SecurityUtils.getSubject();
        
        //new the usernamePasswordToken;
        UsernamePasswordToken token = new UsernamePasswordToken(usrName,"123");
        
        // login == validated the usrName and password
        try {
        	subject.login(token);
		} catch (Exception e) {
			//e.getStackTrace();
		}
        
        //isAuthenticated  是否授权  islogin
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
        
        System.out.println("test successfully!");
        //pls remember to logout;
        //subject.logout();
	}
	
	
	@Test
	public void testHelloWorld(){
		
		String firstIni = "shiro-realm.ini";
		//commonSecurity(firstIni,"Healthy");
		
		String firstRealm = "shiro-realm.ini";
		//commonSecurity(firstRealm,"Healthy183");
		
		String multiRealm = "shiro-multi-realm.ini";
		//commonSecurity(multiRealm,"Wyheng189");
		
		String jdbcRealm = "shiro-jdbc-realm.ini";
		commonSecurity(jdbcRealm,"zhang");
		
		//subject.logout();
		
	}
	
	//@Test(expected = UnknownAccountException.class)
	 public void testAllSuccessfulStrategyWithFail() {
		 
		/*
		 authenticator=org.apache.shiro.authc.pam.ModularRealmAuthenticator
		 securityManager.authenticator=$authenticator

		 #指定securityManager.authenticator的authenticationStrategy
		 allSuccessfulStrategy=org.apache.shiro.authc.pam.AllSuccessfulStrategy
		 securityManager.authenticator.authenticationStrategy=$allSuccessfulStrategy

		 myRealmOne=com.kang.shiro.realm.MyRealmOne
		 myRealmTwo=com.kang.shiro.realm.MyRealmTwo
		 myRealmThree=com.kang.shiro.realm.MyRealmThree
		 securityManager.realms=$myRealmOne,$myRealmThree
		 */ 
		 org.apache.shiro.authc.pam.AllSuccessfulStrategy allSuccessfulStrategy;
		 
		String allFail = "shiro-authenticator-all-fail.ini";
		commonSecurity(allFail,"Wyheng189");
	
	   // Subject subject = SecurityUtils.getSubject();  
		
		//subject.logout();
	} 
	 
	 //@Test
	 public void testAllSuccessfulStrategy() {
		
		 org.apache.shiro.authc.pam.AllSuccessfulStrategy allSuccessfulStrategy;
		 
		//testAllSuccessfulStrategy 
		String allSuccess = "shiro-authenticator-all-success.ini";
		commonSecurity(allSuccess,"Healthy183");
		
		commonGetListMsg(); //Healthy183,Healthy183@kang.com 
	  
	}
	 
	//@Test
	public void testAtLeastOneSuccessful(){
		
		org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy atLeastOneSuccessfulStrategy;
		
		String allSuccess = "shiro-authenticator-atLeastOne-success.ini";
		commonSecurity(allSuccess,"Healthy183");
		
		commonGetListMsg(); //Healthy183,Healthy183@kang.com 
	} 
	
	//@Test
	public void testFirstOneSuccessful(){
		
		org.apache.shiro.authc.pam.FirstSuccessfulStrategy firstSuccessfulStrategy;
		
		String allSuccess = "shiro-authenticator-first-success.ini";
		commonSecurity(allSuccess,"Healthy183");
		
		commonGetListMsg(); //Healthy183
		
	}
	
	
	//@Test
	public void testOnlyOneSuccessful(){
		
		OnlyOneAuthenticatorStrategy onlyOneAuthenticatorStrategy;
		
		String allSuccess = "shiro-authenticator-onlyOne-success.ini";
		
		commonSecurity(allSuccess,"Wyheng189");
		
		commonGetListMsg(); //Wyheng189
		
	}

	//@Test
	public void atLeastTwoSuccessful(){
		
		
		String allSuccess = "shiro-authenticator-atLeastTwo-success.ini";
		
		commonSecurity(allSuccess,"Healthy183");//if MyRealmFour return Healthy183 then the listSize is 2; 
		
		commonGetListMsg(); //Wyheng189
		
	}
	 
	 
	 
	 
	public void commonGetListMsg(){
		  
	    PrincipalCollection  principalCollection  = subject.getPrincipals();
	    
	    List list =	principalCollection.asList();
	    
	    for(Object obj : list){
	    	System.out.println(obj.toString()); 
	    }
	    
	    Assert.assertEquals(2, principalCollection.asList().size());  
	   // subject.logout();
	}
	
	 @After
    public void tearDown() throws Exception {
		 
		subject.logout();
		
		//退出时请解除绑定Subject到线程 否则对下次测试造成影响
        ThreadContext.unbindSubject();
    }
	
}
