package com.kang.boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kang.boot.dao.po.Order;

public interface OrderDao extends JpaRepository<Order, Integer>,OrderDaoCustom {//,IOrderDaoCustom

	Order findByCode(int i);

	
	
	
}
