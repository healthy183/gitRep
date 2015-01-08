package com.kang.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealmOne implements Realm {

	public String getName() {
		return "myRealmOne";
	}

	public boolean supports(AuthenticationToken token) {
		//attention:only support the usernamePasswordToken
		return token instanceof UsernamePasswordToken;
	}

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		
		
	 	String usrName =(String)token.getPrincipal();//get usrName 
		String usrPassword =new String((char[])token.getCredentials());//get usrPwd;
	 	
		if(!"Healthy183".equals(usrName)){
			throw new UnknownAccountException();
		}
		
		if(!"123".equals(usrPassword)){
			throw new IncorrectCredentialsException();
		}
		
		//if vaidated successfully return the AuthenticationInfo;
		//SimpleAuthenticationInfo 
		return new SimpleAuthenticationInfo(usrName,usrPassword,getName());
	}

}
