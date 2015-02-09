package com.kang.boot.comon.dao;

import java.util.List;

import javax.persistence.Query;

public interface BaseJpaDao<M extends java.io.Serializable, PK extends java.io.Serializable>  {

	
	public List queryHql(String hql, Object... paramlist);
	
	public Page pageHqlQuery(String hql, int pageNo, int pageSize,Object... paramlist);
	
	public int execteBulk(final String hql, final Object... paramlist);
	
	public int execteNativeBulk(final String natvieSQL,final Object... paramlist);
	
	public List querySql(String natvieSQL, Object... paramlist);
	
	public Page pageSqlQuery(String hql, int pageNo, int pageSize,Object... paramlist);
	
	
}
