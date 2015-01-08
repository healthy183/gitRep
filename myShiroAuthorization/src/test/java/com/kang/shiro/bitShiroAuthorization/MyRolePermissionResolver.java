package com.kang.shiro.bitShiroAuthorization;

import java.util.Arrays;
import java.util.Collection;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

public class MyRolePermissionResolver  implements RolePermissionResolver{

	public Collection<Permission> resolvePermissionsInRole(String roleString) {

		if("role1".equals(roleString)){
			
		/* Permission permission = new WildcardPermission("menu:*");
		 return	Arrays.asList(permission);*/
		 
	     return Arrays.asList((Permission)new WildcardPermission("menu:*"));
		}
		
		return null;
	}

}
