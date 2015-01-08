package com.kang.shiro.myShiroRealmJDBC.permissionDao;

import com.kang.shiro.myShiroRealmJDBC.model.Permission;

public interface IpermissionDao {

	
	  public Permission createPermission(Permission permission);

	  public void deletePermission(Long permissionId);

	
}
