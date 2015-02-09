package com.kang.boot.dao;

import java.util.List;

import com.kang.boot.dao.po.Order;

public interface OrderDaoCustom {

	
	public List<Order> findAllBySql();
}
