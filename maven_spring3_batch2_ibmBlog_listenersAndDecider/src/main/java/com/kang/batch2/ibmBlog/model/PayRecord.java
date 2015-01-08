package com.kang.batch2.ibmBlog.model;

import lombok.Data;

@Data
public class PayRecord implements java.io.Serializable {

	private Long id;
	private Bill bill;
	private Double paidFees;
	
	
}
