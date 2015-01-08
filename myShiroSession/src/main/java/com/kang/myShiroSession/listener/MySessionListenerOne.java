package com.kang.myShiroSession.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class MySessionListenerOne implements SessionListener {

	
	//create
	public void onStart(Session session) {
		System.out.println("session create:"+session.getId());
	}

	//timeout
	public void onStop(Session session) {
		System.out.println("session stop:"+session.getId());
	}

	//timeout or logout
	public void onExpiration(Session session) {
		System.out.println("session timeout:"+session.getId());
			
	}

	
	
	
	
	

}
