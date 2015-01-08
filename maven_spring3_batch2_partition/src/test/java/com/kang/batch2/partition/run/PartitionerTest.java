package com.kang.batch2.partition.run;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PartitionerTest {

	
	public static void main(String[] args) {
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("partitionJob");
		
		Map<String,JobParameter> parameters = new HashMap<String,JobParameter>(); 
		parameters.put("parameter"+Math.random()*9999,new JobParameter("value"+Math.random()*9999));
		 
	 
		try {
	 
		  JobExecution execution = jobLauncher.run(job, new JobParameters(parameters));
		  System.out.println("Exit Status : " + execution.getStatus());
		  System.out.println("Exit Status : " + execution.getAllFailureExceptions());
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
