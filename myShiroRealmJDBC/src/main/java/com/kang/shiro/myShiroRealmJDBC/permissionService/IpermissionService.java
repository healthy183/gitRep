package com.kang.shiro.myShiroRealmJDBC.permissionService;

import com.kang.shiro.myShiroRealmJDBC.model.Permission;


public interface IpermissionService {

    public Permission createPermission(Permission permission);
    public void deletePermission(Long permissionId);

	
	
	
}
