package maven_spring3_batch2_helloworld;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMysqlBatch {

	
	public static void main(String[] args) {
		


		  ApplicationContext context = new ClassPathXmlApplicationContext(
	                "applicationContext.xml");
	        JobLauncher launcher = (JobLauncher) context.getBean("jobLauncher");
	        Job job = (Job) context.getBean("mysqlJob");

	        try {
	            // JOB实行
	            JobExecution result = launcher.run(
	                    job,
	                    new JobParametersBuilder()
	                            .addString("id",
	                                    "1")
	                            .toJobParameters());
	            // 运行结果输出
	            System.out.println(result.toString());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	
		
		
	}
}
