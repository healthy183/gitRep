package com.kang.test.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Service;

import com.kang.test.dao.ItestDao;
import com.kang.test.model.SysUsers;

@Service("userDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ItestDao testDao;

	@Autowired
	@Qualifier("messageSourceApp")
	private MessageSource messageSource;

	@Autowired
	private UserCache userCache;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		
		AccessDeniedHandlerImpl accessDeniedHandlerImpl;

		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		SysUsers user = (SysUsers) this.userCache.getUserFromCache(username);

		if (user == null) {
			user = this.testDao.getByUsername(username);
			if (user == null)
				throw new UsernameNotFoundException(
						this.messageSource.getMessage(
								"UserDetailsService.userNotFount",
								new Object[] {username}, null));
			// 得到用户的权限
			auths = this.testDao.loadUserAuthorities(username);

			user.setAuthorities(auths);
		}

		this.userCache.putUserInCache(user); 
		
		
		return user;
	}

}
