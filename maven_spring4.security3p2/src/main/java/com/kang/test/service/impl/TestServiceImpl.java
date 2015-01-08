package com.kang.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kang.test.dao.ItestDao;
import com.kang.test.model.SysUsers;
import com.kang.test.service.ItestService;


@Service
public class TestServiceImpl implements ItestService {

	@Autowired
	private ItestDao testDao;
	
	public 	List<SysUsers>  findAlluser() {
		
		
		List<SysUsers> users = testDao.findAlluser();
		
		return users;
	}

	
	
	

}
