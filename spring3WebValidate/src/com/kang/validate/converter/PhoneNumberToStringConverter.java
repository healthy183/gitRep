package com.kang.validate.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.kang.validate.model.PhoneNumberModel;

//PhoneNumberModel------>String
@Component
public class PhoneNumberToStringConverter implements Converter<PhoneNumberModel, String> {

	@Override
	public String convert(PhoneNumberModel source) {
		
		if(source == null){
			return "";
		}
		
		return new StringBuilder()
			.append(source.getAreaCode()).append("-")
				.append(source.getPhoneNumber()).toString();
	}

}
