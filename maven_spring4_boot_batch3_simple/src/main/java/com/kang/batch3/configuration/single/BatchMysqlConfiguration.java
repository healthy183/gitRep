package com.kang.batch3.configuration.single;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kang.batch3.model.Person;
import com.kang.batch3.processor.PersonItemProcessor;

//@Configuration
//@EnableBatchProcessing
public class BatchMysqlConfiguration {

	@Bean
	@Qualifier("scvReader")
	public ItemReader<Person> scvReader(){
		
		FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>();
	
		reader.setResource(new ClassPathResource("sample-data.csv"));
	
		reader.setLineMapper(new DefaultLineMapper<Person>(){{
			
			setLineTokenizer(new DelimitedLineTokenizer(){{
				setNames(new String[]{"fisrtName","lastName"});
			}});
			
			setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>(){{
				setTargetType(Person.class);
			}});
			
		}});
		return reader;
	}
	
	@Bean
	@Qualifier("scvProcessor")
	public ItemProcessor<Person,Person> scvProcessor(){
		return new PersonItemProcessor();
	}
	
	@Bean //<Person>
	@Qualifier("scvWriter")
	public ItemWriter<Person> scvWriter(DataSource dataSource){
	
		JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>();
		
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
		writer.setSql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)");
		writer.setDataSource(dataSource);
		
		return writer;
		
	}
	
	@Bean
	@Qualifier("importUserJob")
	public Job importUserJob(JobBuilderFactory jobs, Step thisStep){
		return jobs.get("importUserJob")
				   .incrementer(new RunIdIncrementer())
				   .flow(thisStep)
				   .end()
				   .build();
	}
	@Bean(name="thisStep")
	@Qualifier("thisStep")
	public Step thisStep(StepBuilderFactory stepBuilderFactory, ItemReader<Person> reader,
            ItemWriter<Person> writer, ItemProcessor<Person, Person> processor){
		
		return stepBuilderFactory
				.get("thisStep")
				.<Person,Person>chunk(10)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
			
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}
	
	@Bean
	public DataSource getDataSource(){
		
		BasicDataSource dataSource = new BasicDataSource();
	
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/springbatch3?useunicode=true&amp;characterEncoding=utf8");
	    dataSource.setUsername("root");
	    dataSource.setPassword("Qq123456");
		
		return dataSource;
	}
	
	
}
