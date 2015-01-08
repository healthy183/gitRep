package com.kang.base.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.kang.tool.Page;
//通用dao
public interface IbaseDao<M extends java.io.Serializable, PK extends java.io.Serializable> {
    
    public void save(Object model);

    public void saveOrUpdate(Object model);
    
    public void update(Object model);
    
    //public void merge(M model);
    public M merge(M model);
    
    //根据id删除对象
    public void deleteById(PK id);

    public void delete(Object model);

    public M get(PK id);
    
    public M load(PK id);

  //将实体PO从缓冲中清除？
    public void initialize(Object entity);
    
    public void saveAll(List<?> list);
    
    public int countAll();
    
    public List<M> findAll();
    
    public List<M> findAll(int pn, int pageSize);
    
    public Page findAllForPage(int pn);
    
    public Page findAllForPage(int pn, int pageSize) ;
    
	public Page pageQuery(String hql, int pageNo, int pageSize, Object... paramlist);
		
	public Page pageQueryWithIn(String hql, int pageNo, int pageSize,
			final Map<String, Collection<?>> map, final Object... paramlist);
			
	public List queryHql(String hql, Object... paramlist);
	
	public List queryHqlWithIn(final String hql,
			final Map<String, Collection<?>> map, Object... paramlist);
	
	public  void setParameterList(Query query,Map<String, Collection<?>> map);
	
	public Query createQuery(String hql, Object[] paramlist);
	
	public List querySql(String natvieSQL,  Object... paramlist);
	
	public void flush();
	
	public void clear();
	
	public boolean exists(PK id);
	
	
	
	public List findByProperty(String propertyName,Object object);

	public List<M> pre(PK pk, int pn, int pageSize);
	
	public List<M> next(PK pk, int pn, int pageSize);
}


