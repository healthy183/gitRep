package com.kang.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.kang.base.dao.IbaseDao;
import com.kang.base.service.ext.BaseHibernateService;
import com.kang.model.test.TbUserDetail;
import com.kang.model.test.TdUser;
import com.kang.tool.Page;
import com.kang.user.dao.ITDuserDao;
import com.kang.user.dao.ITbUserDetailDao;
import com.kang.user.service.ITbUserDetailService;

@Scope("prototype")
@Repository("tdUserDetailServiceImpl")
public class TbUserDetailServiceImpl extends
		BaseHibernateService<TbUserDetail, Integer> implements
		ITbUserDetailService {

	private ITbUserDetailDao tbUserDetailDaoImpl;

	@Override
	@Autowired
	public void setBaseDao(IbaseDao<TbUserDetail, Integer> baseDao) {
		this.baseDao = baseDao;
		this.tbUserDetailDaoImpl = (ITbUserDetailDao) baseDao;

	}

}
