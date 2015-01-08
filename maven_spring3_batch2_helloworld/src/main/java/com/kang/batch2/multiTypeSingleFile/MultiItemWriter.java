package com.kang.batch2.multiTypeSingleFile;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.kang.batch2.model.Goods;
import com.kang.batch2.model.Student;

public class MultiItemWriter<T> implements ItemWriter<T>{

	/** 写代理 */
	 private List<ItemWriter<? super T>> delegates;
	 
	 public void setDelegates(List<ItemWriter<? super T>> delegates) {
		this.delegates = delegates;
	 }

	public void write(List<? extends T> items) throws Exception {
		
		 ItemWriter studentWriter = (ItemWriter) delegates.get(0);
		
		 ItemWriter goodsWriter = (ItemWriter) delegates.get(1);
		
		 List<Student> studentList = new ArrayList<Student>();
		 
		 List<Goods> goodsList = new ArrayList<Goods>();
		 
		 for (int i = 0; i < items.size(); i++) {
	            if ("Student".equals(items.get(i).getClass().getSimpleName())) {
	                studentList.add((Student) items.get(i));
	            } else {
	                goodsList.add((Goods) items.get(i));
	            }
	        }
	        // 如果学生List中有数据，就执行学生信息的写
	        if (studentList.size() > 0) {
	            studentWriter.write(studentList);
	        }
	        // 如果商品List中有数据，就执行商品信息的写
	        if (goodsList.size() > 0) {
	            goodsWriter.write(goodsList);
	        }
	}

}
