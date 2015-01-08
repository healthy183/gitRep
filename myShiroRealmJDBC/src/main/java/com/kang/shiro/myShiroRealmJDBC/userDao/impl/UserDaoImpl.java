package com.kang.shiro.myShiroRealmJDBC.userDao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.kang.shiro.myShiroRealmJDBC.model.UserModel;
import com.kang.shiro.myShiroRealmJDBC.tool.JdbcTemplateUtils;
import com.kang.shiro.myShiroRealmJDBC.userDao.IuserDao;

public class UserDaoImpl implements IuserDao {
	
	private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();
	
	
	public UserModel createUser(final UserModel user) {
		
		final String  insertsql = 
				"insert into sys_users(username, password, salt, locked) values(?,?,?, ?)";
		
	   GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
	       
	   jdbcTemplate.update(new PreparedStatementCreator() {
		
		public PreparedStatement createPreparedStatement(Connection connection)
				throws SQLException {
			
			 PreparedStatement psst = connection.prepareStatement(insertsql, new String[] { "id" });
             psst.setString(1, user.getUserName());
             psst.setString(2, user.getUserPassword());
             psst.setString(3, user.getUserSalt());
             psst.setBoolean(4, user.getIsLocked());
             return psst;
			
		}
	},keyHolder);
	
	   user.setUserId(keyHolder.getKey().longValue());
	   
		return user;
	}

	

	public void correlationRoles(Long userId, Long... roleIds) {

        if(roleIds == null || roleIds.length == 0) {
            return;
        }
        String sql = "insert into sys_users_roles(user_id, role_id) values(?,?)";
        for(Long roleId : roleIds) {
            if(!exists(userId, roleId)) {
                jdbcTemplate.update(sql, userId, roleId);
            }
        }
    
		
	}

	public void uncorrelationRoles(Long userId, Long... roleIds) {
	

        if(roleIds == null || roleIds.length == 0) {
            return;
        }
        String sql = "delete from sys_users_roles where user_id=? and role_id=?";
        for(Long roleId : roleIds) {
            if(exists(userId, roleId)) {
                jdbcTemplate.update(sql, userId, roleId);
            }
        }
	}
	
	
	  private boolean exists(Long userId, Long roleId) {
	        String sql = "select count(1) from sys_users_roles where user_id=? and role_id=?";
	        return jdbcTemplate.queryForObject(sql, Integer.class, userId, roleId) != 0;
	    }

	public UserModel findUerByName(String userName) {

        String sql = "select id, username, password, salt, locked from sys_users where username=?";
        
        final List<UserModel> userList =  new ArrayList<UserModel>();
        /* the db field mustbe equals model filed name  */
       /* List<UserModel> userList = jdbcTemplate.query
        		(sql, new BeanPropertyRowMapper(UserModel.class), userName);*/
        
        
        Object[] objArray = new Object[]{userName};
        int[] intArray = new int[]{Types.VARCHAR};
        
        jdbcTemplate.query(sql,objArray,intArray,new RowCallbackHandler(){

			public void processRow(ResultSet rs)
					throws SQLException {
				
				UserModel user = new UserModel();
				user.setUserId(rs.getLong(1));
				user.setUserName(rs.getString(2));
				user.setUserPassword(rs.getString(3));
				user.setUserSalt(rs.getString(4));
				user.setIsLocked(rs.getBoolean(5));
				userList.add(user);
			}
        	
        });
        
        
        if(userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

	public Set<String> findRole(String userName) {
	    String sql = "select role from sys_users u, sys_roles r,sys_users_roles ur where u.username=? and u.id=ur.user_id and r.id=ur.role_id";
        return new HashSet(jdbcTemplate.queryForList(sql, String.class, userName));
   
	}

	public Set<String> findPermission(String userName) {
	     //TODO 此处可以优化，比如查询到role后，一起获取roleId，然后直接根据roleId获取即可
        String sql = "select permission from sys_users u, sys_roles r, sys_permissions p, sys_users_roles ur, sys_roles_permissions rp where u.username=? and u.id=ur.user_id and r.id=ur.role_id and r.id=rp.role_id and p.id=rp.permission_id";
        return new HashSet(jdbcTemplate.queryForList(sql, String.class, userName));
	}



	public void updateUser(UserModel user) {
	     String sql = "update sys_users set username=?, password=?, salt=?, locked=? where id=?";
	     jdbcTemplate.update(sql, user.getUserName(),
	    		 user.getUserPassword(), user.getUserSalt(), 
	    		 	user.getIsLocked(), user.getUserId());
	}



	
	public void deleteUser(Long userId) {
		String sql = "delete from sys_users where id=?";
        jdbcTemplate.update(sql, userId);		
	}

	
	
	

	public UserModel findOne(Long userId) {
		

        String sql = "select id, username, password, salt, locked from sys_users where id=? ";
        
        /*List<UserModel> userList = jdbcTemplate.query
        		(sql, new BeanPropertyRowMapper(UserModel.class), userId);*/
        
        final List<UserModel> userList =  new ArrayList<UserModel>();
        
        Object[] objArray = new Object[]{userId};
        int[] intArray = new int[]{Types.LONGNVARCHAR};
        
        jdbcTemplate.query(sql,objArray,intArray,new RowCallbackHandler(){

			public void processRow(ResultSet rs)
					throws SQLException {
				
				UserModel user = new UserModel();
				user.setUserId(rs.getLong(1));
				user.setUserName(rs.getString(2));
				user.setUserPassword(rs.getString(3));
				user.setUserSalt(rs.getString(4));
				user.setIsLocked(rs.getBoolean(5));
				userList.add(user);
			}
        	
        });
        
        if(userList.size() == 0) {
            return null;
        }
        return userList.get(0);
		
	}




}
