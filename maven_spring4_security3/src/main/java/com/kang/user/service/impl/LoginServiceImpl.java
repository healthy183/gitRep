package com.kang.user.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kang.base.dao.IbaseDao;
import com.kang.base.service.ext.BaseHibernateService;
import com.kang.model.SysUsers;
import com.kang.model.vo.SysUsersVo;
import com.kang.user.dao.IloginDao;
import com.kang.user.service.IloginService;
//UserDetailsService , 
@Scope("prototype")
@Service
public class LoginServiceImpl
	extends BaseHibernateService<SysUsers, Integer>
		implements IloginService{
	
	private IloginDao iloginDao;
	
	@Override
	@Autowired
	public void setBaseDao(IbaseDao<SysUsers, Integer> baseDao) {
		this.baseDao = baseDao;
		this.iloginDao = (IloginDao) baseDao;
	}

	/*@Autowired  
    private MessageSource messageSource;  
      
    @Autowired  
    private UserCache userCache; */
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		/*
		
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();  
		
		SysUsersVo userVo = (SysUsersVo) this.userCache.getUserFromCache(username); 
		
		if(userVo == null){  
			SysUsers  user = (SysUsers) iloginDao.findByProperty("username", username);
            if(user == null)  
                throw new UsernameNotFoundException(this.messageSource.getMessage(  
                        "UserDetailsService.userNotFount", new Object[]{username}, null));  
            //得到用户的权限  
            auths = this.iloginDao.loadUserAuthorities(username);  
              
            userVo.setAuthorities(auths);  
        } 
		
		 this.userCache.putUserInCache(userVo);  
		
		
		return userVo;
	*/
		return null;
	}



	
	
	
	
}
