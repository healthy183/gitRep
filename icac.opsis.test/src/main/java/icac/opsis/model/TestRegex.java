package icac.opsis.model;

import org.apache.commons.lang.StringUtils;

public class TestRegex {

	
	public static void main(String[] args) {
		
		String regexStr = "As121 !@Bd? dq/ Dd\"";
	
		String resultString = regexStr.replaceAll("(?i)[^a-zA-Z0-9\u4E00-\u9FA5]", ""); 
		
		System.out.println(resultString);
		
		//String  s = "/^[a-z]{1}\w{4,14}$/";
		
		resultString =  StringUtils.uncapitalize(resultString);
		
		System.out.println(resultString);
		
		
		
	}
	
}
