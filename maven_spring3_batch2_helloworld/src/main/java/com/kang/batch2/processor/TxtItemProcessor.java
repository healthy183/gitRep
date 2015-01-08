package com.kang.batch2.processor;

import org.springframework.batch.item.ItemProcessor;

import com.kang.batch2.model.StudentTxt;

public class TxtItemProcessor implements  ItemProcessor<StudentTxt,StudentTxt>{

	
	
	public StudentTxt process(StudentTxt student) throws Exception {
		 /* 合并ID和名字 */
        student.setName(student.getID() + "--" + student.getName());
        /* 年龄加2 */
        student.setAge(student.getAge() + 2);
        /* 分数加10 */
        student.setScore(student.getScore() + 10);
        /* 将处理后的结果传递给writer */
        return student;
	}
	
	
	

}
