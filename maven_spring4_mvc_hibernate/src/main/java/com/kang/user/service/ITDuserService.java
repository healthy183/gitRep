package com.kang.user.service;

import java.util.List;

import com.kang.base.service.IbaseService;
import com.kang.model.test.TdUser;

public interface ITDuserService extends IbaseService<TdUser,Integer>{

	String login(TdUser user);

	List<TdUser> allInfo();

}
