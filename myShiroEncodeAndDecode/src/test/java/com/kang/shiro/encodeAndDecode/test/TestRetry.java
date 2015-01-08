package com.kang.shiro.encodeAndDecode.test;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.junit.Test;

public class TestRetry extends BaseTest {

	
	@Test //(expected = ExcessiveAttemptsException.class)
	public void mainTest(){
		
		for(int i =0;i<=2;i++){
			
			try {
			    login("classpath:shiro-retryLimitHashedCredentialsMatcher.ini", "liu", "144");
			} catch (Exception e) {
				
			}
		}
		
				login("classpath:shiro-retryLimitHashedCredentialsMatcher.ini", "liu", "123");
	}
}

