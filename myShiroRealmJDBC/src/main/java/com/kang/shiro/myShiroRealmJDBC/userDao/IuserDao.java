package com.kang.shiro.myShiroRealmJDBC.userDao;

import java.util.Set;

import com.kang.shiro.myShiroRealmJDBC.model.UserModel;


public interface IuserDao {
	
	/**
	 * @param user
	 * @return
	 */
	public UserModel createUser(UserModel user);
	
	
	/**
	 * @param user
	 */
	public void updateUser(UserModel user);
	

    
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
     * @param userId
     * @return
     */
    public UserModel findOne(Long userId);
    
    
    
    
    /**
     * @param userName
     * @return 
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
