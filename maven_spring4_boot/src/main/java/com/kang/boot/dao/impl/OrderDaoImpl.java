package com.kang.boot.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.kang.boot.comon.dao.BaseJpaDaoImpl;
import com.kang.boot.dao.OrderDaoCustom;
import com.kang.boot.dao.po.Order;

//@Repository
public class OrderDaoImpl extends  BaseJpaDaoImpl<Order,Integer>  implements OrderDaoCustom {

	@PersistenceContext(unitName="orderPersistenceUnit")
	private EntityManager entityManager;
	
	public  EntityManager getEntityManager() {
		return entityManager;
	}
	
	
	/*@Autowired
	@Qualifier("orderEntityManager")
	private EntityManager em;*/
	
	/*@Autowired
	@Qualifier(value="orderEntityManager")
	private LocalContainerEntityManagerFactoryBean  emFaction;*/
	
	/*public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}*/



	public List<Order> findAllBySql(){
		System.out.println("11");
		//EntityManager em = 	emFaction.getNativeEntityManagerFactory().createEntityManager();
		//return
		
		String sql = "select * from orders";
		
		//List<Object[]> objs  = em.createNativeQuery("select * from orders").getResultList();
		
		//List<Order> obj  = em.createNativeQuery("select * from orders").getResultList();
		//return  em.createNamedQuery("select * from order").getResultList();
		System.out.println("---------------");
		
		List<Order> objss  =  querySql(sql, null);
		
		return objss;
	}



	
	
	
}
