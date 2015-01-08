package com.kang.newFour.dao.impl;

import org.springframework.stereotype.Repository;

import com.kang.newFour.dao.InewFourDao;
import com.kang.newThreePointOne.model.IocNewUser;

@Repository
public class NewFourDaoImpl 
	extends BaseHibernateDao<IocNewUser, Integer>
			implements InewFourDao {

}
