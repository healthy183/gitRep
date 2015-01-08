package com.kang.base.service.ext;

import java.util.Collection;
import java.util.List;



import com.kang.base.dao.IbaseDao;
import com.kang.base.service.IbaseService;
import com.kang.tool.Page;

public abstract class 
	BaseHibernateService<M extends java.io.Serializable, PK extends java.io.Serializable> 
		implements IbaseService<M, PK> {

	 protected IbaseDao<M, PK> baseDao;
	 public abstract void setBaseDao(IbaseDao<M, PK> baseDao);

	 public void saveAll(List<?> list){
		 baseDao.saveAll(list);
	 }
	 
	 
	
	public void save(Object model) {
		baseDao.save(model);
	}

	
	public void saveOrUpdate(Object model) {
		baseDao.saveOrUpdate(model);		
	}

	
	public void update(Object model) {
		baseDao.update(model);		
	}

	
	public M merge(M model) {
		return baseDao.merge(model);
	}

	
	public void delete(PK id) {
		baseDao.deleteById(id);		
	}

	
	public void deleteObject(Object model) {
		baseDao.delete(model);
	}

	
	public M get(PK id) {
		return baseDao.get(id);
	}

	
	public M load(PK id){
		return baseDao.load(id);
	}
	
	
	public int countAll() {
		return baseDao.countAll();
	}

	
	public List<M> findAll() {
		return baseDao.findAll();
	}

	
	public List<M> findAll(int pn, int pageSize) {
		return baseDao.findAll(pn, pageSize);
	}
	
	
	public Page findAllForPage(int pn) {
		return baseDao.findAllForPage(pn);
	}
	
	
	public Page findAllForPage(int pn, int pageSize) {
		return baseDao.findAllForPage(pn,pageSize);
	}
	

	
	
	
	
	
	public Page pre(PK pk, int pn, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Page next(PK pk, int pn, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Page pre(PK pk, int pn) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Page next(PK pk, int pn) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
