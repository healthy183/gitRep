package com.kang.boot.db.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.kang.boot.db.CustomerDaoCustomer;

public class CustomerDaoImpl implements CustomerDaoCustomer {
	
	
	@PersistenceContext(unitName="customerPersistenceUnit")
	private EntityManager em;
	
}
