package testweb_tomcat;

public class StringBuilderTest {

	public static void main(String[] args) {
		
		//javax.servlet.Filter f =new javax.servlet.Filter();
		
		

		StringBuilder data = new StringBuilder();
		data.append("{");
		data.append("\"type\" : \"msg\"");
		data.append(",\"username\" : \"" + "kang" + "\"");
		data.append(",\"msg\" : \"" + "hello" + "\"");
		data.append("}");
		
		System.out.println(data.toString());
		
		 int i = 0;
		 
		 StringBuilderTest t =new StringBuilderTest();
		 t.testGood(i);
		 i++;
		 System.out.println(i);
		 
		 String s   = new String("a");
		 String b   = new String("b");
		 
		 s = null;
		 
		 System.out.println(b+""+s);
		 
		 
		 t.testGood(b);
		 System.out.println(b);
	}
	
	public void testGood(int i){
		i++;
		System.out.println(i);
	}
	
	
	public void testGood(String i){
		i =i+"s";
	}

}
