package com.kang.user.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.kang.base.dao.ext.BaseHibernateDao;
import com.kang.model.SysAuthorities;
import com.kang.model.SysUsers;
import com.kang.user.dao.IloginDao;
@Scope("prototype")
@Repository
public class LoginDaoImpl extends BaseHibernateDao<SysUsers,Integer> implements IloginDao {

	public Collection<GrantedAuthority> loadUserAuthorities(String username){ 
        List<SysAuthorities> list = this.getSysAuthoritiesByUsername(username);  
          
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();  
          
        for (SysAuthorities authority : list) {  
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthorityMark());  
            auths.add(grantedAuthority);  
        }  
  
        return auths;  
          
    } 
	
	@SuppressWarnings("unchecked")  
    private List<SysAuthorities> getSysAuthoritiesByUsername(String username){  
        String sql = "SELECT * FROM SYS_AUTHORITIES WHERE AUTHORITY_ID IN( "+  
                "SELECT DISTINCT AUTHORITY_ID FROM SYS_ROLES_AUTHORITIES  S1 "+  
                "JOIN SYS_USERS_ROLES S2 ON S1.ROLE_ID = S2.ROLE_ID "+  
                "JOIN SYS_USERS S3 ON S3.USER_ID = S2.USER_ID AND S3.USERNAME=?)";  
        return  querySql(sql,username);
    } 
	
	
	
}
