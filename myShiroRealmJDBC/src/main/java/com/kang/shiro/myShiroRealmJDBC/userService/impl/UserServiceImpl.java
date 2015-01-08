package com.kang.shiro.myShiroRealmJDBC.userService.impl;

import java.util.Set;

import com.kang.shiro.myShiroRealmJDBC.model.UserModel;
import com.kang.shiro.myShiroRealmJDBC.tool.PasswordHelper;
import com.kang.shiro.myShiroRealmJDBC.userDao.IuserDao;
import com.kang.shiro.myShiroRealmJDBC.userDao.impl.UserDaoImpl;
import com.kang.shiro.myShiroRealmJDBC.userService.IuserService;

public class UserServiceImpl implements IuserService {
	
	private IuserDao userDao =  new UserDaoImpl();
	private PasswordHelper passwordHelper = new PasswordHelper();
	
	public UserModel createUser(UserModel user) {
		
		passwordHelper.encryptPassword(user);
		return userDao.createUser(user);
	}

	public void changePassword(Long userId, String newPassword) {

			UserModel user =userDao.findOne(userId);
	        user.setUserPassword(newPassword);
	        passwordHelper.encryptPassword(user);
	        userDao.updateUser(user);
	}

	public void correlationRoles(Long userId, Long... roleIds) {
		userDao.correlationRoles(userId, roleIds);
	}

	public void uncorrelationRoles(Long userId, Long... roleIds) {
	     userDao.uncorrelationRoles(userId, roleIds);
	}

	public UserModel findUerByName(String userName) {
	     return userDao.findUerByName(userName);
	}

	public Set<String> findRole(String userName) {
		 return userDao.findRole(userName);
	}

	public Set<String> findPermission(String userName) {
		 return userDao.findPermission(userName);
	}

}
