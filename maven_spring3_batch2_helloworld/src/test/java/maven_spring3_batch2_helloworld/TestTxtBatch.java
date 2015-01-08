package maven_spring3_batch2_helloworld;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestTxtBatch {

	public static void main(String[] args) {
		

		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//HelloWorld job = (HelloWorld)context.getBean("hello");
		//System.out.println(job.getMessage());
		JobLauncher jobLauncher  = (JobLauncher) context.getBean("jobLauncher");
		
		 Job job = (Job)context.getBean("txtJob");
		
		 try {
	        	
	        	String url = new TestXMLBatch().getClass().getClassLoader().getResource("xmlIOBatchInput.xml").toString();
	        	
	            JobExecution result = jobLauncher.run(job, new JobParametersBuilder()
	                    //.addString("inputFilePath", url.toString())
	            		.addString("inputFilePath", "classpath:txtIOBatchInput.txt")
	            		.addString("outputFilePath", "file:src/txtIOBatchOutput.txt")
	                    .toJobParameters());
	            // 运行结果输出
	            System.out.println(result.toString());
	        
		 } catch (JobExecutionAlreadyRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobRestartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
