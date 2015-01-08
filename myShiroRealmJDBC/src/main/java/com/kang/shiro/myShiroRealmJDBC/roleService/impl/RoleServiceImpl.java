package com.kang.shiro.myShiroRealmJDBC.roleService.impl;

import com.kang.shiro.myShiroRealmJDBC.model.Role;
import com.kang.shiro.myShiroRealmJDBC.roleDao.IroleDao;
import com.kang.shiro.myShiroRealmJDBC.roleDao.impl.RoleDaoImpl;
import com.kang.shiro.myShiroRealmJDBC.roleService.IroleService;

public class RoleServiceImpl implements IroleService {


    private IroleDao roleDao = new RoleDaoImpl();


    public Role createRole(Role role) {
        return roleDao.createRole(role);
    }

    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }

    /**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        roleDao.correlationPermissions(roleId, permissionIds);
    }

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        roleDao.uncorrelationPermissions(roleId, permissionIds);
    }


}
