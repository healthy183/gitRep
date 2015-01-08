package com.kang.batch2.ibmBlog.model;

import java.io.Serializable;

import lombok.Data;
@Data
public class User implements Serializable {

	private String name;
	private Integer age;
	
}
