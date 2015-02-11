package com.kang.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kang.boot.dao.po.Order;
import com.kang.boot.dao.vo.User;
import com.kang.boot.db.po.Customer;
import com.kang.boot.service.IOrderService;

//@EnableAutoConfiguration
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private	IOrderService orderService;
	
	
	//http://localhost:9090/user/findCustomer
		@RequestMapping("/findCustomer") 
		public ModelAndView findCustomer(){
			
			List<Customer>  customerList = orderService.findCustomer();
			ModelAndView mv = new ModelAndView();
			mv.addObject("customerList",customerList);
			
			mv.setViewName("customer/findCustomer");
			
			return mv;
		}
	
	
	
	//http://localhost:9090/user/1
	@RequestMapping("/{id}")  
	public User view(@PathVariable("id") Long id){
		
		User user = new User();
		user.setId(id);
		user.setName("Healthy梁健康");
		
		System.out.println(user.toString());
		
		return user;
	}
	
	//http://localhost:9090/user/findAllBySql
	@RequestMapping("findAllBySql")  
	public List<Order> findAllBySql() {
		return  orderService.findAllBySql();
	}
	
	//http://localhost:9090/user/findAllOrder
	@RequestMapping("/findAllOrder") 
	public List<Order> findAllOrder(){
		
		List<Order>  orderList = orderService.findAll();
		return orderList;
	}
	
	
	//http://localhost:9090/user/saveOrder
	@RequestMapping("/saveOrder") 
	public Order saveOrder(){
		return orderService.saveOrder();
	}
	
	//http://localhost:9090/user/updateOrder
	@RequestMapping("/updateOrder") 
	public Order updateOrder(){
		return orderService.updateOrder();
	}
	
	//http://localhost:9090/user/deleteOrder/18
	@RequestMapping("/deleteOrder/{id}")
	public Order deleteOrder(@PathVariable("id") int id){
		return orderService.deleteOrder(id);
	}
	
	
	//should add "@EnableAutoConfiguration" in once class run!
	/*
	public static void main(String[] args) {
		SpringApplication.run(UserController.class);  
		
	}*/
	
}
