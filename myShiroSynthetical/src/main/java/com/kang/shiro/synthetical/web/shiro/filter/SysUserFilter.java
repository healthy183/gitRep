package com.kang.shiro.synthetical.web.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.kang.shiro.synthertical.Constants;
import com.kang.shiro.synthertical.entity.User;
import com.kang.shiro.synthertical.service.UserService;


public class SysUserFilter  extends PathMatchingFilter {

	@Autowired
    private UserService userService;
	
	 @Override
	    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

	        String username = (String)SecurityUtils.getSubject().getPrincipal();
	        
	        User user = 	userService.findByUsername(username);
	        
	        request.setAttribute(Constants.CURRENT_USER, user);
	        return true;
	    }
	
	
	
}
