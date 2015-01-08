package com.kang.user.dao;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.kang.base.dao.IbaseDao;
import com.kang.model.SysUsers;

public interface IloginDao extends IbaseDao<SysUsers,Integer> {

	Collection<GrantedAuthority> loadUserAuthorities(String username);

	
}
