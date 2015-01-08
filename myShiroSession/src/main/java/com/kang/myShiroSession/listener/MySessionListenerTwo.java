package com.kang.myShiroSession.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

public class MySessionListenerTwo extends SessionListenerAdapter {


	@Override
	public void onStart(Session session) {
		System.out.println("session create:"+session.getId());
	}

}
