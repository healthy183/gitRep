package com.kang.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.kang.base.dao.IbaseDao;
import com.kang.base.service.ext.BaseHibernateService;
import com.kang.model.SysUsers;
import com.kang.model.vo.SysUsersVo;
import com.kang.tool.Page;
import com.kang.user.dao.IuserDao;
import com.kang.user.service.IuserService;


@Scope("prototype")
@Service("userServiceImpl")
public class UserServiceImpl 
	extends BaseHibernateService<SysUsers,Integer> 
			implements IuserService {

	
	
	private  IuserDao userDaoImpl;
	
	@Override
	@Autowired
	@Qualifier("userDaoImpl")
	public void setBaseDao(IbaseDao<SysUsers, Integer> baseDao) {
		this.baseDao =  baseDao;
		this.userDaoImpl = (IuserDao) baseDao;
	}

	 //post创建user
	public Integer addUser(String usrName, String usrPwd) {
		SysUsers user = new SysUsers();
		user.setUsrname(usrName);
		user.setUsrpwd(usrPwd);
		save(user);
		return user.getUsrid();
	}

	 //更新用户信息
	public void updtusr(String userId, String updtusrName, String updtusrPwd) {
		SysUsers user  =  get(Integer.valueOf(userId));
		user.setUsrname(updtusrName);
		user.setUsrpwd(updtusrPwd);
		merge(user);
	}

	 //删除用户
	public void delusr(String userId) {
			delete(Integer.valueOf(userId));
	}

	//使用存储过程查询
	public List<SysUsersVo> findAllUserByPro() {
		return userDaoImpl.findAllUserByPro();
	}
	


}
