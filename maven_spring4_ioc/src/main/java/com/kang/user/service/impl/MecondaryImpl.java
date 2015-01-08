package com.kang.user.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kang.user.service.ImainService;

@Order(value=1)
@Scope("prototype")
@Service("imecondaryImpl")
@Transactional
public class MecondaryImpl implements ImainService {
	
	public String lgnin() {
		System.out.println("this is spring4 imecondaryImpl lgnin!");
		return "mecondary";
	}

	public void changePassword(String password, String confirmation) {
		
	}

}
