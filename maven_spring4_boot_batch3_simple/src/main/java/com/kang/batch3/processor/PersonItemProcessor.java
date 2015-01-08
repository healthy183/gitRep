package com.kang.batch3.processor;

import org.springframework.batch.item.ItemProcessor;

import com.kang.batch3.model.Person;

public class PersonItemProcessor implements ItemProcessor<Person,Person> {

	public Person process(Person person) throws Exception {
		
		String  firstName = person.getFirstName().toUpperCase();
		String lastName   = person.getLastName().toUpperCase();
		Person rp = new Person(firstName,lastName);
		return rp;
	}

}
