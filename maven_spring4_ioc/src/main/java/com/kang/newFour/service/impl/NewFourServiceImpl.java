package com.kang.newFour.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.kang.newFour.dao.InewFourDao;
import com.kang.newFour.service.NewFourService;
import com.kang.newThreePointOne.model.IocNewUser;

@Service
@Qualifier("newFourServiceImpl")
public class NewFourServiceImpl 
	extends BaseHibernateService<IocNewUser, Integer>
		implements NewFourService {

	/*@Override
	public void setBaseDao(IBaseDao<NewUser, Integer> baseDao) {
		
	}

	@Autowired
   private InewFourDao newFourDaoImpl;*/
	
	public  void testSave(){
		//newFourDaoImpl.save(new IocNewUser());
	}
	
	
	public static void main(String[] args) {
		
		ApplicationContext app  = new ClassPathXmlApplicationContext("applicationContext.xml");
		NewFourService newFourServiceImpl = app.getBean("newFourServiceImpl",NewFourService.class);
		
		newFourServiceImpl.save(new IocNewUser());
	}
	
}
