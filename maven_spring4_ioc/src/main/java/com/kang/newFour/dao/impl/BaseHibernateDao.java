package com.kang.newFour.dao.impl;

import com.kang.newFour.dao.IBaseDao;

public class BaseHibernateDao<M extends java.io.Serializable, PK extends java.io.Serializable> 
	implements IBaseDao<M, PK> {

	public void save(Object model) {
		System.out.println("BaseHibernateDao save Object!");
	}

}
