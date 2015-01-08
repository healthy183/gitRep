package com.kang.user.dao;

import java.util.List;

import com.kang.base.dao.IbaseDao;
import com.kang.model.SysUsers;
import com.kang.model.vo.SysUsersVo;

public interface IuserDao extends IbaseDao<SysUsers,Integer> {

	List<SysUsersVo> findAllUserByPro();

	
}
