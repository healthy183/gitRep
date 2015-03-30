package com.kang.shiro.myShiroAuthorization;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

public class PermissionTest extends RoleTest{
	
	//@Test
	public void isPermission(){
		
		login("shiro-permission.ini", "zhang", "123");
		
		Subject subject = getSubject();
		
		Assert.assertTrue(subject.isPermitted("user:create"));
		
		boolean isTrue = subject.isPermittedAll("user:create","user:update","user:update");
		
		Assert.assertTrue(isTrue);
	}
	
	//@Test
	public void testCheckPermission(){
		
		login("shiro-permission.ini", "zhang", "123");
		
		Subject subject = getSubject();
		
		subject.checkPermission("user:create");
	
		subject.checkPermissions("user:create","user:delete");
		
		//@Test(expected = UnauthorizedException.class) 
		subject.checkPermissions("user:create","user:view");
		
	}
	
	//@Test
	public void testWildcardPermissionLi4(){
		
		login("shiro-permission.ini", "li4", "123");
		
		Subject subject = getSubject();
		
		subject.checkPermissions("system:user:upadte","system:user:delete");
		
		subject.checkPermissions("system:user:update,delete");
	}
	
	//@Test
	public void testWildcardPermissionLi5(){
		
		login("shiro-permission.ini", "li5", "123");
		
		Subject subject = getSubject();
		
		subject.checkPermissions("system:user:create,update,delete,view");
		
		subject.checkPermissions("system:user:create,update,delete,view,ko");
		
		subject.checkPermissions("system:user:*");
		
		subject.checkPermissions("system:user");
	}
	
	//@Test
	public void testWildcardPermissionLi6(){
		
		login("shiro-permission.ini", "li6", "123");
		
		Subject subject = getSubject();
		
		//no permission
		//subject.checkPermissions("system:user:create,update,delete,view");
		
		subject.checkPermissions("system:view");
		
		subject.checkPermissions("*:view");
		
		subject.checkPermissions("system:create:view");
		
		subject.checkPermissions("*:*:view");
	}
	
	//@Test
	public void testWildcardPermissionLi7(){
		
		login("shiro-permission.ini", "li7", "123");
		
		Subject subject = getSubject();
		
		subject.checkPermissions("user:view:1");
		
		subject.checkPermissions("user:delete,update:1");
		
		subject.checkPermissions("user:delete:1","user:update:1");
		
		subject.checkPermissions("user:delete,update,view:1");
		
		//subject.checkPermissions("system:delete,update,view:1");
		
		subject.checkPermissions("user:auth:1");
		
		subject.checkPermissions("user:auth:*");
		
		subject.checkPermissions("user:a:b");
		
		subject.checkPermissions("user:*:*");
		
	}
	
	//@Test
	public void testWildcardPermissionLi8(){
		
		login("shiro-permission.ini", "li8", "123");
		
		Subject subject = getSubject();
		
		subject.checkPermissions("menu:*");
		
		subject.checkPermissions("menu:*:*");
		
		subject.checkPermissions("organization");
		
		subject.checkPermissions("organization:view");
		
		subject.checkPermissions("organization:view:1");

		subject.checkPermissions("organization:*");
		
		subject.checkPermissions("organization:*:*");
	}
	
	
	@Test
    public void testNewWildcardPermission() {
        login("shiro-permission.ini", "li8", "123");
        
    	Subject subject = getSubject();
    	
        subject.checkPermission("menu:view:1");
        
        subject.checkPermission(new WildcardPermission("menu:view:1"));

    }
}
