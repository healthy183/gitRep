package com.kang.test.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.kang.test.model.SysUsers;

public interface ItestDao {

	List<SysUsers> findAlluser();

	Collection<GrantedAuthority> loadUserAuthorities(String username);

	SysUsers getByUsername(String username);

}
