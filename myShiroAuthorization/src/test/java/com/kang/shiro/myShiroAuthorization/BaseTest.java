package com.kang.shiro.myShiroAuthorization;

import junit.framework.Assert;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;

public abstract class BaseTest {
	
	@After
	public void tearDown(){
		
		getSubject().logout();
		//退出时请解除绑定Subject到线程 否则对下次测试造成影响
		ThreadContext.unbindSubject();
		
	}
	
	protected void login(String configFile,String userName,String userPwd){
		
		 Factory<org.apache.shiro.mgt.SecurityManager> factory =
				 new IniSecurityManagerFactory("classpath:"+configFile);
		 
		 org.apache.shiro.mgt.SecurityManager securityManager =	factory.getInstance();
		 
		 SecurityUtils.setSecurityManager(securityManager);
		 
		 Subject subject = getSubject();
		
		 UsernamePasswordToken token = new UsernamePasswordToken(userName,userPwd);
	        
		 subject.login(token);
		 
		 Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
	        
		 //System.out.println("test successfully!");
		 
		 
	}
	
	
	
	
	public Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	

}
