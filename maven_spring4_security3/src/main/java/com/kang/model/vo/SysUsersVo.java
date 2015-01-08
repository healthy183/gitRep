package com.kang.model.vo;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.kang.model.SysUsers;
//extends SysUsers
public class SysUsersVo   implements UserDetails {

	private Collection<GrantedAuthority>  authorities; 
	
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	
	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public boolean isAccountNonExpired() {
		return false;
	}

	public boolean isAccountNonLocked() {
		return false;
	}

	public boolean isCredentialsNonExpired() {
		return false;
	}

	public boolean isEnabled() {
		return false;
	}


	public String getPassword() {
		//return getPassword();
		return null;
	}

	public String getUsername() {
		//return getUsername();
		return null;
	}

}
