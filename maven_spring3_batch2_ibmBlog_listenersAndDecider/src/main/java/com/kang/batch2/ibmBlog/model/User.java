package com.kang.batch2.ibmBlog.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User implements java.io.Serializable {

	private Long id;
	private String name;
	private Integer age;
	private Double balance;
	
	
		
}
