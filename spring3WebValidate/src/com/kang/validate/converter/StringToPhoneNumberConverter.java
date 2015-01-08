package com.kang.validate.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.kang.validate.model.PhoneNumberModel;

public class StringToPhoneNumberConverter implements
		Converter<String, PhoneNumberModel> {

	Pattern pattern = Pattern.compile("^(\\d{3,4})-(\\d{7,8})$");

	@Override
	public PhoneNumberModel convert(String source) {

		if (!StringUtils.hasLength(source)) {
			return null;
		}

		Matcher matcher = pattern.matcher(source);

		if (matcher.matches()) {

			PhoneNumberModel phoneNumber = new PhoneNumberModel();
			phoneNumber.setAreaCode(matcher.group(1));
			phoneNumber.setPhoneNumber(matcher.group(2));
			return phoneNumber;

		} else {

			throw new IllegalArgumentException(String.format(
					"类型转换失败，需要格式[010-12345678]，但格式是[%s]", source));

		}

	}

}
