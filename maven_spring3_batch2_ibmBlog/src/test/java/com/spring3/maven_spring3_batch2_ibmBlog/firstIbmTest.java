package com.spring3.maven_spring3_batch2_ibmBlog;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class firstIbmTest {
	
	
  public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//HelloWorld job = (HelloWorld)context.getBean("hello");
		//System.out.println(job.getMessage());
		JobLauncher jobLauncher  = (JobLauncher) context.getBean("jobLauncher");
		
		 Job job = (Job)context.getBean("firstIbmJob");
		
		 try {
			 Map<String,JobParameter> parameters = new HashMap<String,JobParameter>(); 
			 parameters.put("parameter",new JobParameter("2011-10"));
			 
			  JobExecution result = jobLauncher.run(job, new JobParameters(parameters));
			  
			  Thread.sleep(5000); 
			  //第一次第七个user nanme为空，所以抛异常，然后修改userRead.txt，retry成功！
			  JobExecution  je = jobLauncher.run(job, new JobParameters(parameters));
			 // System.out.println(result.toString());
			   
			   System.out.println(je); 
			   System.out.println(je.getJobInstance()); 
			   System.out.println(je.getStepExecutions());
		 
		 } catch (JobExecutionAlreadyRunningException e) {
			e.printStackTrace();
		} catch (JobRestartException e) {
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 
		
		
		
	
	  
	  
}
  
  
}
