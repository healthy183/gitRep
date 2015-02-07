package com.kang.boot.dao;

import java.util.List;

import com.kang.boot.po.Order;

public interface OrderDaoCustom {

	
	public List<Order> findAllBySql();
}
