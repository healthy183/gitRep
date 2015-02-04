package com.kang.batch3.configuration.single;

import java.io.File;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.kang.batch3.model.Person;
import com.kang.batch3.processor.PersonItemProcessor;


//@Configuration
//@EnableBatchProcessing
public class BatchTxtConfiguration {

	
	@Bean(name="imporTxtJob")
	@Qualifier("imporTxtJob")
	public Job imporTxtJob(JobBuilderFactory jobs, Step txtStep){
		return jobs.get("imporTxtJob")
				   .incrementer(new RunIdIncrementer())
				   .flow(txtStep)
				   .end()
				   .build();
	}
	
	
	@Bean(name="txtStep")
	@Qualifier("txtStep")
	public Step txtStep(StepBuilderFactory stepBuilderFactory, ItemReader<Person> reader,
            ItemWriter<Person> writer, ItemProcessor<Person, Person> processor){
		
		return stepBuilderFactory
				.get("txtStep")
				.<Person,Person>chunk(10)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
	
	@Bean
	@Qualifier("txtProcessor")
	public ItemProcessor<Person,Person> txtProcessor(){
		return new PersonItemProcessor();
	}
	
	
	@Bean
	@Qualifier("txtReader")
	public ItemReader<Person> txtReader() {
        FlatFileItemReader<Person> itemReader = new FlatFileItemReader<Person>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setResource(new ClassPathResource("input_data.txt"));
        return itemReader;
    }
	
	//@Bean
	public LineMapper<Person> lineMapper() {
	        DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<Person>();
	        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
	        lineTokenizer.setNames(new String[]{"lastName","firstName"});
	        lineTokenizer.setIncludedFields(new int[]{0,1});
	        BeanWrapperFieldSetMapper<Person> fieldSetMapper = new BeanWrapperFieldSetMapper<Person>();
	        fieldSetMapper.setTargetType(Person.class);
	        lineMapper.setLineTokenizer(lineTokenizer);
	        lineMapper.setFieldSetMapper(fieldSetMapper);
	        return lineMapper;
	    }
	
	@Bean
	@Qualifier("txtWriter")
	public ItemWriter<Person> txtWriter() {
        FlatFileItemWriter<Person> itemWriter = new FlatFileItemWriter<Person>();
        DelimitedLineAggregator<Person> la = new DelimitedLineAggregator<Person>();
        la.setDelimiter(",");
        BeanWrapperFieldExtractor<Person> fieldExtractor = new BeanWrapperFieldExtractor<Person>();
        fieldExtractor.setNames(new String[]{"lastName","firstName"});
        la.setFieldExtractor(fieldExtractor);
        itemWriter.setLineAggregator(la);
        
        
        itemWriter.setResource(new FileSystemResource(new File("src/output_data.txt")));
       /// itemWriter.setResource( new ClassPathResource("output_data.txt"));
        //itemWriter.setResource(new FileSystemResource(new File("output_data.txt")));
        return itemWriter;
    }
	
	
	
}
