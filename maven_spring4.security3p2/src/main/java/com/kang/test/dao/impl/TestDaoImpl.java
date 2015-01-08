package com.kang.test.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.kang.test.dao.ItestDao;
import com.kang.test.model.SysAuthorities;
import com.kang.test.model.SysUsers;

@Repository
public class TestDaoImpl implements ItestDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<SysUsers> findAlluser() {
		
		Session session = sessionFactory.getCurrentSession();
		
		String sql ="select username,password from sys_users";
		
		List<Object[]> objs = session.createSQLQuery(sql).list();
		
		SysUsers user = null;
		
		List<SysUsers> usrList = new ArrayList<SysUsers>();
		
		for(Object[] obj : objs){
			
			user = new SysUsers();
			
			String usrName = obj[0]!=null?obj[0].toString():"";
			
			System.out.println(usrName);
			
			user.setUsername(usrName);
			
			usrList.add(user);
		}
		
		return usrList;
	}
	
	public Collection<GrantedAuthority> loadUserAuthorities(String username){  
        List<SysAuthorities> list = this.getSysAuthoritiesByUsername(username);  
          
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();  
          
        for (SysAuthorities authority : list) {  
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthorityMark());  
            auths.add(grantedAuthority);  
        }  
  
        return auths;  
          
    }
	
	
	private List<SysAuthorities> getSysAuthoritiesByUsername(String username){  
		
        /*String sql = "SELECT AUTHORITY_ID,AUTHORITY_MARK,AUTHORITY_NAME, "
        		+ "AUTHORITY_DESC,MESSAGE,ENABLE "
        		+ "ISSYS,MODULE_ID "*/
		String sql = "SELECT AUTHORITY_MARK "
        		+" FROM SYS_AUTHORITIES WHERE AUTHORITY_ID IN( "+  
                "SELECT DISTINCT AUTHORITY_ID FROM SYS_ROLES_AUTHORITIES  S1 "+  
                "JOIN SYS_USERS_ROLES S2 ON S1.ROLE_ID = S2.ROLE_ID "+  
                "JOIN SYS_USERS S3 ON S3.USER_ID = S2.USER_ID AND S3.USERNAME=:USERNAME)";  
          
        Session session = sessionFactory.getCurrentSession();
        
        List<SysAuthorities> authoritiesList = new ArrayList<SysAuthorities>();
        
        List<Object[]> objs = session.createSQLQuery(sql)
        		.setParameter("USERNAME", username).list();
        
      SysAuthorities sysAuthorities = null;
        
      for(Object[] obj : objs){
    	  	
    	  sysAuthorities = new SysAuthorities();
    	  String mark = obj[0]!=null?obj[0].toString():"";
    	  sysAuthorities.setAuthorityMark(mark);
    	  authoritiesList.add(sysAuthorities);
      }
        
        return authoritiesList;  
    }

	public SysUsers getByUsername(String username) {
		
		Session session = sessionFactory.getCurrentSession();
		
		String sql ="select username,password from sys_users where username = :username";
		
		List<Object[]> objs = session.createSQLQuery(sql)
				.setParameter("username", username).list();
		
		SysUsers user = null;
		
		List<SysUsers> usrList = new ArrayList<SysUsers>();
		
		for(Object[] obj : objs){
			
			user = new SysUsers();
			
			String usrName = obj[0]!=null?obj[0].toString():"";
			
			System.out.println(usrName);
			
			user.setUsername(usrName);
			
			usrList.add(user);
		}
		
		return CollectionUtils.isEmpty(usrList)?null:usrList.get(0);
	}

}
