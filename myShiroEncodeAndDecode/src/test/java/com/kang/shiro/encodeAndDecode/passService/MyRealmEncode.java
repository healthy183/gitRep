package com.kang.shiro.encodeAndDecode.passService;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyRealmEncode  extends AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		
		String usrname = "liu"; // or salt1
		
		String password ="a9a114054aa6758184314fbb959fbda4";
		
		String saltTwo = "24520ee264eab73ec09451d0e9ea6aac";
		
		SimpleAuthenticationInfo ai =   
				new SimpleAuthenticationInfo(usrname,password,getName());
		
		ai.setCredentialsSalt(ByteSource.Util.bytes(usrname+saltTwo));
		
		return ai;
	}

	
	
}
