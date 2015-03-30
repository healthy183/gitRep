package com.kang.batch2.partition.run;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TxtRun {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("partitionTxtJob");

		Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
		parameters.put("parameter" + Math.random() * 9999, new JobParameter(
				"value" + Math.random() * 9999));
		parameters.put("inputFilePath", new JobParameter(
				"classpath:txtIOBatchInput.txt"));

		Date startDate = new Date();

		try {

			JobExecution execution = jobLauncher.run(job, new JobParameters(
					parameters));
			System.out.println("Exit Status : " + execution.getStatus());
			System.out.println("Exit Status : "
					+ execution.getAllFailureExceptions());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Date endDate = new Date();
		
		System.out.println((endDate.getTime() - startDate.getTime())/1000);
		//DateFormatUtils.

	}

		
			
		
	
	
	/*public static void main(String[] args) throws IOException {
		
		File dirFile = new File("csv/outputs");
		
		//FileInputStream fis=new FileInputStream(dirFile);
		
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(dirFile));
		
		dos.close();
		
		//fis.close();
		
		if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        
			System.out.println("非目录");
	    }
		
		String fileUri = dirFile.toURL().toString();
		
		System.out.println("URL:"+fileUri);
		
		//System.out.println(.delete());
		
		TxtRun.deleteFile(fileUri);
		
		
		//System.out.println(new File("csv/outputs").toURI());
		
		
		
		
	}*/

	
	public static void deleteFile(String path) {  
	    File file = new File(path);  
	    if (file.isDirectory()) {  
	        File[] ff = file.listFiles();  
	        for (int i = 0; i < ff.length; i++) {  
	            deleteFile(ff[i].getPath());  
	        }  
	    }  
	    boolean isOk = file.delete();  
	    System.out.println(isOk);
	} 


}
