package com.kang.user.dao;

import java.util.List;

import com.kang.base.dao.IbaseDao;
import com.kang.model.test.TdUser;

public interface ITDuserDao extends IbaseDao<TdUser,Integer> {

	List<TdUser> login(TdUser user);

	List<TdUser> allInfo();

}
