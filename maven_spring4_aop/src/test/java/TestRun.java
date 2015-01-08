import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kang.service.AfterAdvice;


public class TestRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		BeanFactory  factory = new ClassPathXmlApplicationContext("applicationContext.xml");  
		AfterAdvice afterAdviceImpl = factory.getBean("afterAdviceImpl",AfterAdvice.class);
		//afterAdviceImpl.testAfterAdvice();
		//afterAdviceImpl.testStatedAfterAdvice("god");
		//afterAdviceImpl.testReturnAfterAdice("yes");
		afterAdviceImpl.testExceptionAfterAdice();
	}

}
