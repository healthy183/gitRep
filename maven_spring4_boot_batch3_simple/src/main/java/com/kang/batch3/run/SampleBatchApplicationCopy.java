package com.kang.batch3.run;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@SpringBootApplication
@Configuration
@EnableBatchProcessing
public class SampleBatchApplicationCopy {

	@Autowired
	private JobBuilderFactory jobBF;
	
	@Autowired
	private StepBuilderFactory stepBF;
	
	@Bean
	protected Tasklet tasklet() {
			
		return new Tasklet(){

			public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
					throws Exception {
				return RepeatStatus.FINISHED;
			}
		};
	}
	
	@Bean(name="firstStepCopy")
	protected Step step1() throws Exception {
		return this.stepBF.get("firstStepCopy").tasklet(tasklet()).build();
	}
	
	@Bean(name="firstJobCopy")
	public Job job() throws Exception{
		
		return this.jobBF.get("firstJobCopy").start(step1()).build();
		
	}
	
	/*public static void main(String[] args) {
		
		System.exit(SpringApplication.exit(SpringApplication.run(
				SampleBatchApplication.class, args)));
	}*/
	
	
	
	
	
}
