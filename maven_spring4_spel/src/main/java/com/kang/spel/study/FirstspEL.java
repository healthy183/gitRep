package com.kang.spel.study;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;


import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

public class FirstspEL {

	public void hellword(){
		
		ExpressionParser parser = new SpelExpressionParser();
		
		Expression expression =	parser.parseExpression("('Hello' + ' World').concat(#end)");
	
		EvaluationContext context = new StandardEvaluationContext();
		
		context.setVariable("end","!");
		
		String val = (String) expression.getValue(context);
		
		System.out.println(val);//Hello world!
		
	}
	
	public void testParserContext(){
		
		ExpressionParser parser = new SpelExpressionParser();  
		
		ParserContext parserContext = new ParserContext() {

			public String getExpressionPrefix() {
				return "#{";
			}

			public String getExpressionSuffix() {
				return "}";
			}

			public boolean isTemplate() {
				return true;
			}
			
		}; 
		
		String template  = "#{'Hello '}#{'World,Healthy!'}";
		
		Expression expression  = parser.parseExpression(template, parserContext);
	
		String val = (String) expression.getValue();	

		System.out.println(val);//Hello World,Healthy!
	}
	
	
	
	public void baseType4String(){
		
		ExpressionParser parser = new SpelExpressionParser();  
		
		String strVal = parser.parseExpression("'Hello World!'").getValue(String.class);
		System.out.println(strVal);//Hello World!
		
		String helloW = parser.parseExpression("\"Hello David!\"").getValue(String.class);
		System.out.println(helloW);//Hello David!
	}
	
	public void baseType4Num(){
		
		ExpressionParser parser = new SpelExpressionParser();  
		int intVal = parser.parseExpression("1").getValue(Integer.class);
		System.out.println("int:"+intVal);//int:1
		
		long longVal = parser.parseExpression("-1L").getValue(Long.class);
		System.out.println("long:"+longVal);//long:-1
		
		float longVal2 = parser.parseExpression("1.1").getValue(Float.class);
		System.out.println("float:"+longVal2);//float:1.1
		
		double double1 = parser.parseExpression("1.1E+2").getValue(double.class);
		System.out.println("double:"+double1);//double:110.0
		
		int hex1 = parser.parseExpression("0xa").getValue(Integer.class);
		System.out.println("hex1:"+hex1);//hex1:10
		
		long hex2 = parser.parseExpression("0xaL").getValue(long.class);
		System.out.println("hex2:"+hex2);//hex2:10
	}
	
	public void baseType4Boolean(){
		
		ExpressionParser parser = new SpelExpressionParser();
		Boolean isFalse = parser.parseExpression("FalsE").getValue(Boolean.class);
		System.out.println("parser:"+(isFalse?"F":"T"));//T
		
		Object null1 = parser.parseExpression("null").getValue(Object.class);
		
		System.out.println("isNull:"+( null1 == null));//true
	}
	
	public void baseType4operation(){
		
		ExpressionParser parser = new SpelExpressionParser();
		int result1 = parser.parseExpression("1+2-3*4/2").getValue(Integer.class);//-3
		
		System.out.println("result1:"+result1);
		
		int result2 = parser.parseExpression("4%3").getValue(Integer.class);//1
		
		System.out.println("result2:"+result2);
		
		int result3 = parser.parseExpression("2^3").getValue(Integer.class);//8
		System.out.println("result3:"+result3);
		
	}
	
	public void logicPoint(){
		//do not support  && 和 || 。
		ExpressionParser parser = new SpelExpressionParser();
		
		String expression1 = "2>1 and (!true or !false)";  
		
		boolean result1 = parser.parseExpression(expression1).getValue(boolean.class); 
		
		System.out.println(result1);
		
		String expression2 = "2>1 and (NOT true or NOT false)";  
		
		boolean result2 = parser.parseExpression(expression2).getValue(boolean.class);  
		
		System.out.println(result2);
	}
	
	public void testClass(){
		
		ExpressionParser parser = new SpelExpressionParser(); 
		//java.lang包类访问 
		Class<String> result1 = parser.parseExpression("T(String)").getValue(Class.class);
		
		System.out.println(result1.getClass().toString());
		//其他包类访问 
		String expression2 = "T(org.springframework.expression.ExpressionParser)";
		
		Class<String> result2 = parser.parseExpression(expression2).getValue(Class.class);
		
		System.out.println(result2);
		//类静态字段访问 
		int result3=parser.parseExpression("T(Integer).MAX_VALUE").getValue(int.class); 
		
		System.out.println("result3:"+result3);
		 //类静态方法调用  
		 int result4 = parser.parseExpression("T(Integer).parseInt('1')").getValue(int.class); 
		
		 System.out.println("result4"+result4);
	}
	
	public void testConstructor(){
		
		ExpressionParser parser = new SpelExpressionParser(); 
		
		 String result1 = parser.parseExpression("new String('haha')").getValue(String.class); 

		 System.out.println("result:"+result1);//haha
		 
		 Date result2 = parser.parseExpression("new java.util.Date()").getValue(Date.class);
		 
		System.out.println("time:"+DateFormatUtils.format(result2, "yyyy-MM-dd")); //2014-01-18
	}
	
	
	public void testVariable(){
		
		
		 ExpressionParser parser = new SpelExpressionParser();  
		 
		 EvaluationContext context = new StandardEvaluationContext();  
		 
		 context.setVariable("variable", "haha");  
		 
		 String result1 = parser.parseExpression("#variable").getValue(context, String.class);
		
		 System.out.println(result1);//haha
		
		 context = new StandardEvaluationContext("haha2");  
		 
		 String result2 = parser.parseExpression("#root").getValue(context, String.class);
		 
		 System.out.println(result2);//haha2
		 
		 String result3 = parser.parseExpression("#this").getValue(context, String.class);
		 
		 System.out.println(result3);//haha2
		
	}
	
	public void testFunctionExpression() throws SecurityException, NoSuchMethodException{
		
		
		 ExpressionParser parser = new SpelExpressionParser();
		 
		 StandardEvaluationContext context = new StandardEvaluationContext();  
		
		 Method parseInt = Integer.class.getDeclaredMethod("parseInt", String.class);
		 
		 context.registerFunction("parseInt", parseInt);  
		 
		 context.setVariable("parseInt2", parseInt);  
		 
		 String expression1 = "#parseInt('3') == #parseInt2('3')";  
		 
		 boolean result1 = parser.parseExpression(expression1)
				 .getValue(context, boolean.class); 
		 
		 System.out.println(result1);//true
		 
	}
	
	public void testAssign(){
		
		ExpressionParser parser = new SpelExpressionParser();  
		
		//1.给root对象赋值  
		EvaluationContext context = new StandardEvaluationContext("aaaa");  
		
		String result1 = parser.parseExpression("#root='bbbb'")
				.getValue(context, String.class); 
		
		System.out.println(result1);//bbbb
		
		String result2 = parser.parseExpression("#this='cccc'")
				.getValue(context, String.class); 
		
		System.out.println(result2);//cccc
		
		//2.给自定义变量赋值  
		context.setVariable("#variable", "variable");  
		
		String result3 = parser.parseExpression("#variable=#root")
				.getValue(context, String.class);  
		
		System.out.println(result3);//aaaa
		
		
		
	}
	//???
	public void testObject(){
		
		ExpressionParser parser = new SpelExpressionParser(); 
		//1.访问root对象属性 
		Date date = new Date();
		StandardEvaluationContext context = new StandardEvaluationContext(date); 
		
		int result1 = parser.parseExpression("year").getValue(context, int.class);  
		
		int year = date.getYear();
		System.out.println("years:"+year);//114???
		System.out.println("result1:"+result1);//114??
		
		
		System.out.println("formatYear:"+DateFormatUtils.format(date, "yyyy"));
		
		int result2 = parser.parseExpression("year").getValue(context, int.class); 
		
		System.out.println("result2:"+result2);//114
		
		//2.安全访问
		Object result3 = parser.parseExpression("#root?.year").getValue(context, Object.class); 
	
		System.out.println(result3 == null);//false
		
		//2.安全访问
		context.setRootObject(null);
		result3 = parser.parseExpression("#root?.year").getValue(context, Object.class); 
			
		System.out.println(result3 == null);//true
		
		//3.给root对象属性赋值
		context.setRootObject(date);
		int result4 = parser.parseExpression("year = 4").getValue(context, int.class);  
		System.out.println("result4:"+result4);
		
		parser.parseExpression("Year").setValue(context, 5);  
		int result5 = parser.parseExpression("year").getValue(context, int.class);  
		System.out.println("result5:"+result5);
		
		
		//call method();
		 date = new Date();  
		 context = new StandardEvaluationContext(date);  
		 int thisyear = parser.parseExpression("getYear()").getValue(context, int.class);  
		 System.out.println("thisyear:"+thisyear);
	}
	
	public void testBean(){
		
		 ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext();  
		 ctx.refresh(); 
		 ExpressionParser parser = new SpelExpressionParser();  
		 StandardEvaluationContext context = new StandardEvaluationContext();  
		 context.setBeanResolver(new BeanFactoryResolver(ctx));  
		 Properties result1 = parser.parseExpression("@systemProperties").getValue(context, Properties.class);  
		 System.out.println("result1:"+result1);
		 
	}
	
	public void testList(){
		
		ExpressionParser parser = new SpelExpressionParser();  
		//将返回不可修改的空List 
		List<Integer> result2 = parser.parseExpression("{}").getValue(List.class);  
		
		System.out.println("result2:"+CollectionUtils.isEmpty(result2));//true
		
		System.out.println(result2 == null);//false
		
		
		List<Integer> result1 = parser.parseExpression("{1,2,3}").getValue(List.class);  
		
		System.out.println("get(val):"+result1.get(0));
		
		//throw exception,java.lang.UnsupportedOperationException
		//result1.set(0, 2);
		
		//对于列表中只要有一个不是字面量表达式，将只返回原始List，
		String expression3 = "{{1+2,2+4},{3,4+4}}";  
		List<List<Integer>> result3 = parser.parseExpression(expression3).getValue(List.class);

		System.out.println( result3.get(0).get(0));//3
		
		result3.get(0).set(0, 1);  
		 
		System.out.println("size:"+result3.size());//2
		
		System.out.println( result3.get(0).get(0));//1
		 
		 
	}
	
	public void arrayTest(){
		
		ExpressionParser parser = new SpelExpressionParser();  
		//声明二维数组并初始化  
		//new Array();
		int[] result2 = parser.parseExpression("new int[2]{1,2}").getValue(int[].class);  
		
		String arrayList = ArrayUtils.toString(result2);
		System.out.println("arrayList:"+arrayList);//arrayList:{1,2}
		
		//定义一维数组并初始化  
		int[] result1 = parser.parseExpression("new int[1]").getValue(int[].class);  
		String result1List = ArrayUtils.toString(result1);
		System.out.println("arrayList:"+result1List);//arrayList:{0}
		
		//定义多维数组
		String expression3 = "{{1+2,2+4},{3,4+4}}";  
		int[][][] result3 = parser.parseExpression(expression3).getValue(int[][][].class);  
		String result3List = ArrayUtils.toString(result3);
		System.out.println("arrayList:"+result3List);//arrayList:{{{3},{6}},{{3},{8}}}
		
		//错误的定义多维数组，多维数组不能初始化  
		String expression4 = "new int[1][2][3]{{1}{2}{3}}";
		
		/*//org.springframework.expression.spel.SpelParseException: 
		try {	///EL1043E:(pos 20): Unexpected token.  Expected 'rcurly(})' but was 'lcurly({)'
			int[][][] result4 = parser.parseExpression(expression4).getValue(int[][][].class); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		
		int result4 = parser.parseExpression("{1,2,3}[0]").getValue(int.class); 
		System.out.println("getVal:"+(result4 == 1));//true
		
		
		//modify
		int[] array = new int[] {1, 2};  
		EvaluationContext context1 = new StandardEvaluationContext();  
		context1.setVariable("array", array);  
		int result5 = parser.parseExpression("#array[1] = 3").getValue(context1, int.class);
		
		System.out.println("array:"+ArrayUtils.toString(array));//{1,3}
		
		System.out.println("result5:"+result5);//3
		
		
	}
	
	public void testCollection(){
		
		ExpressionParser parser = new SpelExpressionParser();  
		//SpEL目前支持所有集合类型的访问 
		Collection<Integer> collection = new HashSet<Integer>();  
		collection.add(1); 
		collection.add(2); 
		EvaluationContext context2 = new StandardEvaluationContext();  
		context2.setVariable("collection", collection);  
		int result2 = parser.parseExpression("#collection[1]").getValue(context2, int.class); 
		//对于任何集合类型通过Iterator来定位元素 
		System.out.println("result2:"+result2);//2
		
		//SpEL对Map字典元素访问的支持  
		Map<String, Integer> map = new HashMap<String, Integer>();  
		map.put("a", 1);  
		EvaluationContext context3 = new StandardEvaluationContext();  
		context3.setVariable("map", map);  
		int result4 = parser.parseExpression("#map['a']").getValue(context3, int.class); 
		System.out.println("mapresult3:"+result4);//1
		
		//modify map 
		int modifyMapVal = parser.parseExpression("#map['a'] = 3").getValue(context3, int.class); 
		System.out.println("modifyMapVal:"+modifyMapVal);//3
		
		//modify list
		collection = new ArrayList<Integer>();  
		collection.add(1);  
		collection.add(2); 
		context2 = new StandardEvaluationContext();  
		context2.setVariable("collection", collection); 
		int result5 = parser.parseExpression("#collection[1] = 3")
				.getValue(context2, int.class);
		
		System.out.println("result5:"+result5);//3
		
		parser.parseExpression("#collection[1]").setValue(context2, 4);
		
		int result6 = parser.parseExpression("#collection[1]").getValue(context2, int.class); 
		
		System.out.println("result6:"+result6);//4
	}
	
	
	public void  projectionCollection(){
		
		ExpressionParser parser = new SpelExpressionParser(); 
		
		Collection<Integer> collection = new ArrayList<Integer>();  
		collection.add(4); 
		collection.add(5); 
		
		//test collection or list
		EvaluationContext context1 = new StandardEvaluationContext();  
		context1.setVariable("collection", collection);  
		//very element add one 
		Collection<Integer> result1 =  
				parser.parseExpression("#collection.![#this+1]")
					.getValue(context1, Collection.class);
		
		System.out.println("result1:"+result1.size());//2
		System.out.println(result1.toString());//[5,6]
		
		Map<String, Integer> map = new HashMap<String, Integer>();  
		map.put("a", 1);    
		map.put("b", 2);  
		map.put("c", 3); 
		map.put("d", 4); 
		
		EvaluationContext  context2 = new StandardEvaluationContext();
		context2.setVariable("map", map);
		//very element add one  and the map must be return for list
		List<Integer> result2 =  
				parser.parseExpression("#map.![ value+1]")
					.getValue(context2, List.class); 
		
		System.out.println(result2.toString());
		
	}
	
	public void chooseList() {

		// 1.首先准备测试数据
		Collection<Integer> collection = new ArrayList<Integer>();
		collection.add(4);
		collection.add(5);
		
		EvaluationContext context1 = new StandardEvaluationContext(); 
		context1.setVariable("collection", collection);  
		
		ExpressionParser parser = new SpelExpressionParser(); 
		
		Collection<Integer> result1 =  
				parser.parseExpression("#collection.?[#this>4]")
					.getValue(context1, Collection.class);
		
		//#collection.?[#this>4]”将选择出集合元素值大于4的所有元素
		//选择表达式必须返回布尔类型，使用“#this”表示当前元素。
		System.out.println(result1.toString());//[5]
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("a", 1);
		map.put("b", 22);
		map.put("c", 33);
		map.put("d", 44);
		
		EvaluationContext context2 = new StandardEvaluationContext();  
		context2.setVariable("map", map);
		
		Map<String, Integer> result2 =  
				parser.parseExpression("#map.?[#this.key != 'a']")
					.getValue(context2, Map.class);  
		System.out.println(result2.toString());
		
		List<Integer> result3 =  
				 parser.parseExpression("#map.?[key != 'a'].![value+1]")
				 	.getValue(context2, List.class);
		
		System.out.println(result3.toString());
	}
	
	public static void main(String[] args) throws SecurityException, NoSuchMethodException {
		
		FirstspEL f = new FirstspEL();
		//f.hellword();
		//f.testParserContext();
		f.testAssign();
	}
	
}
