package com.kang.shiro.myShiroRealmJDBC.userService;

import java.util.Set;

import com.kang.shiro.myShiroRealmJDBC.model.UserModel;
import com.kang.shiro.myShiroRealmJDBC.userDao.IuserDao;
import com.kang.shiro.myShiroRealmJDBC.userDao.impl.UserDaoImpl;

public interface IuserService {

	
	
	/**
	 * @param user
	 * @return
	 */
	public UserModel createUser(UserModel user);
	

	/**
	 * @param userId
	 * @param newPassword
	 */
    public void changePassword(Long userId, String newPassword);
    
    
    /**
     * @param userId
     * @param roleId
     */
    public void correlationRoles(Long userId,Long... roleId);
    

    
    /**
     * @param userId
     * @param roleId
     */
    public void uncorrelationRoles(Long userId,Long... roleId);
    
	
    /**
     * @param userName
     */
    public UserModel findUerByName(String userName);
   
    
    /**
     * @param userName
     * @return
     */
    public Set<String> findRole(String userName);
    
    
    /**
     * @param userName
     * @return
     */
    public Set<String> findPermission(String userName);
    

}
