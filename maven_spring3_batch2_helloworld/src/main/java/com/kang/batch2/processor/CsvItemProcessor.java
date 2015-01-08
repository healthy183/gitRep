package com.kang.batch2.processor;

import org.springframework.batch.item.ItemProcessor;

import com.kang.batch2.model.Student;

public class CsvItemProcessor implements ItemProcessor<Student, Student> {

	public Student process(Student student) throws Exception {
		
		
		student.setName(student.getID() + "--" + student.getName());
		
		 student.setAge(student.getAge() + 2);
		 
		 student.setScore(student.getScore() + 10);
		
		 return student;

	}

}
