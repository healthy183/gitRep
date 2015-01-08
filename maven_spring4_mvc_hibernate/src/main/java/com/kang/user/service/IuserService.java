package com.kang.user.service;

import java.util.List;

import com.kang.base.service.IbaseService;
import com.kang.model.SysUsers;
import com.kang.model.vo.SysUsersVo;

public interface IuserService extends IbaseService<SysUsers,Integer> {

	Integer addUser(String usrName, String usrPwd);

	void updtusr(String userId, String updtusrName, String updtusrPwd);

	void delusr(String userId);

	List<SysUsersVo> findAllUserByPro();

	
	
}
