package com.kang.shiro.myShiroRealmJDBC.permissionService.impl;

import com.kang.shiro.myShiroRealmJDBC.model.Permission;
import com.kang.shiro.myShiroRealmJDBC.permissionDao.IpermissionDao;
import com.kang.shiro.myShiroRealmJDBC.permissionDao.impl.PermissionDaoImpl;
import com.kang.shiro.myShiroRealmJDBC.permissionService.IpermissionService;

public class PermissionServiceImpl implements IpermissionService {

	
	private IpermissionDao permissionDao = new PermissionDaoImpl();
	
	
	public Permission createPermission(Permission permission) {
	    return permissionDao.createPermission(permission);
	}

	
	
	
	
	public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
	}

}
