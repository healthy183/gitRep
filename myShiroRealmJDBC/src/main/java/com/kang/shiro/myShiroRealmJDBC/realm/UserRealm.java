package com.kang.shiro.myShiroRealmJDBC.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.kang.shiro.myShiroRealmJDBC.model.UserModel;
import com.kang.shiro.myShiroRealmJDBC.userService.IuserService;
import com.kang.shiro.myShiroRealmJDBC.userService.impl.UserServiceImpl;

public class UserRealm extends AuthorizingRealm {

	private IuserService userService = new UserServiceImpl(); 
	
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		String userName = (String)principals.getPrimaryPrincipal();
		
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        authorizationInfo.setRoles(userService.findRole(userName));

        authorizationInfo.setStringPermissions(userService.findPermission(userName));
        
		
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		
		String userName = (String)token.getPrincipal();
		
		UserModel user = userService.findUerByName(userName);
		
		if(user == null){
			throw new UnknownAccountException();
		}
		
		if(Boolean.TRUE.equals(user.getIsLocked())){
			throw new LockedAccountException(); 
		}
		
		/*String dbUserName = user.getUserName();
		
		String password = user.getUserPassword();
		
		String creadentialsSalt = user.getCreadentialsSalt();//userName + salt;
		
		ByteSource byteSource = ByteSource.Util.bytes(creadentialsSalt);
		
		SimpleAuthenticationInfo  authenticationInfo 
		 	= new SimpleAuthenticationInfo(dbUserName,password,byteSource,getName());*/
		
		SimpleAuthenticationInfo  authenticationInfo = new SimpleAuthenticationInfo(
	                user.getUserName(), //用户名
	                user.getUserPassword(), //密码
	                ByteSource.Util.bytes(user.getCreadentialsSalt()),//salt=username+salt
	                getName()  //realm name
	        );
		
		
		return authenticationInfo;
	}

}
