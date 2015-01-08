package com.kang.chatRoom.controller;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener(value = "sessionListener")
public class SessionListener  implements HttpSessionListener{

	
	private MsgPublisher msgPublisher = MsgPublisher.getInstance();
	
	public void sessionCreated(final HttpSessionEvent se) {
		//se.getSession().setMaxInactiveInterval(50); //会话最长50秒
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		// msgPublisher.logout((String)se.getSession().getAttribute("usrName"));
	}

}
