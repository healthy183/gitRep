package com.kang.batch2.model;

import java.io.Serializable;
import java.util.Date;

public class Goods implements Serializable {

	
	private String isin;
	
	private int quantity;
	
	private double price;
	
	private String customer;
	
	private Date buyDay;

	public void setIsin(String isin) {
		this.isin = isin;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public void setBuyDay(Date buyDay) {
		this.buyDay = buyDay;
	}

	public String getIsin() {
		return isin;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public String getCustomer() {
		return customer;
	}

	public Date getBuyDay() {
		return buyDay;
	}
	
	
	
}
