package com.kang.user.dao.ext;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.kang.base.dao.ext.BaseHibernateDao;
import com.kang.model.test.TbUserDetail;
import com.kang.tool.Page;
import com.kang.user.dao.ITbUserDetailDao;

@Scope("prototype")
@Repository("tbUserDetailDaoImpl")
public class TbUserDetailDaoImpl extends BaseHibernateDao<TbUserDetail, Integer> implements
		ITbUserDetailDao {
	
	
	
	
}
