package com.spring3.maven_spring3_batch2_ibmBlog_asyncTask;

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

public class SimpleNextAndListener {

		public static void main(String[] args) {
			
			

			
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			//HelloWorld job = (HelloWorld)context.getBean("hello");
			//System.out.println(job.getMessage());
			JobLauncher jobLauncher  = (JobLauncher) context.getBean("jobLauncher");
			
			// Job job = (Job)context.getBean("billingJobListeners");
			  Job job = (Job)context.getBean("billingJobDecision");
			 Map<String,JobParameter> parameters = new HashMap<String,JobParameter>(); 
			 parameters.put("parameter"+Math.random()*9999,new JobParameter("value"+Math.random()*9999));
			 
			 try {
				 
				  JobExecution rs = jobLauncher.run(job, new JobParameters(parameters));
				  
				  System.out.println(rs.toString());
				  
			 } catch (JobExecutionAlreadyRunningException e) {
				e.printStackTrace();
			} catch (JobRestartException e) {
				e.printStackTrace();
			} catch (JobInstanceAlreadyCompleteException e) {
				e.printStackTrace();
			} catch (JobParametersInvalidException e) {
				e.printStackTrace();
			} 
		}
	
}
