package com.kang.shiro.myShiroRealmJDBC.roleDao;

import com.kang.shiro.myShiroRealmJDBC.model.Role;

public interface IroleDao {

	
	    public Role createRole(Role role);
	    public void deleteRole(Long roleId);

	    public void correlationPermissions(Long roleId, Long... permissionIds);
	    public void uncorrelationPermissions(Long roleId, Long... permissionIds);

	
}
