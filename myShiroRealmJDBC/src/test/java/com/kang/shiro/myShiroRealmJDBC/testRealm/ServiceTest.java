package com.kang.shiro.myShiroRealmJDBC.testRealm;

import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public class ServiceTest extends BaseTest {


    @Test
    public void testUserRolePermissionRelation() {

        //zhang
        Set<String> roles = userService.findRole(u1.getUserName());
        Assert.assertEquals(1, roles.size());
        Assert.assertTrue(roles.contains(r1.getRole()));

        Set<String> permissions = userService.findPermission(u1.getUserName());
        Assert.assertEquals(3, permissions.size());
        Assert.assertTrue(permissions.contains(p3.getPermission()));

        //li s();
        roles = userService.findRole(u2.getUserName());
        Assert.assertEquals(0, roles.size());
        permissions = userService.findPermission(u2.getUserName());
        Assert.assertEquals(0, permissions.size());


        //解除 admin-menu:update关联
        roleService.uncorrelationPermissions(r1.getId(), p3.getId());
        permissions = userService.findPermission(u1.getUserName());
        Assert.assertEquals(2, permissions.size());
        Assert.assertFalse(permissions.contains(p3.getPermission()));


        //删除一个permission
        permissionService.deletePermission(p2.getId());
        permissions = userService.findPermission(u1.getUserName());
        Assert.assertEquals(1, permissions.size());

        //解除 zhang-admin关联
        userService.uncorrelationRoles(u1.getUserId(), r1.getId());
        roles = userService.findRole(u1.getUserName());
        Assert.assertEquals(0, roles.size());


    }

}
