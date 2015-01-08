package com.mavenHiberate4.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.mavenHiberate4.model.SysUsers;

public class OneToMany {

	
	public static void main(String[] args) {

		
		Configuration configuration = new AnnotationConfiguration();
		// 加载配置文件
		configuration.configure("hibernate.cfg.xml");
		
		Session session = configuration.buildSessionFactory().openSession();
		
		
		/*Transaction tx = session.beginTransaction();
		tx.begin();*/
		Transaction tx = session.getTransaction();
		tx.begin();
		
		
		SysUsers leaders = new SysUsers();
		leaders.setUsrname("申请报销员工SSS");
		
		session.save(leaders);
		tx.commit();
		session.close();
		
		
	}

}
