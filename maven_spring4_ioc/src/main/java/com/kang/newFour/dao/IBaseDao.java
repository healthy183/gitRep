package com.kang.newFour.dao;

public interface IBaseDao<M extends java.io.Serializable ,PK  extends java.io.Serializable> {

	
	 public void save(Object model);
}
