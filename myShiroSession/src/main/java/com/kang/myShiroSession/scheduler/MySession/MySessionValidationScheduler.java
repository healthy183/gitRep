package com.kang.myShiroSession.scheduler.MySession;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.AbstractValidatingSessionManager;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ReflectionUtils;

import com.kang.myShiroSession.tools.JdbcTemplateUtils;


public class MySessionValidationScheduler implements SessionValidationScheduler,Runnable {

	
	private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

	private static final Logger log = LoggerFactory.getLogger(MySessionValidationScheduler.class);

	ValidatingSessionManager sessionManager;
	
	private ScheduledExecutorService service;
	
	private long interval = DefaultSessionManager.DEFAULT_SESSION_VALIDATION_INTERVAL;
    
	private boolean enabled = false;
	
	
	

	public ValidatingSessionManager getSessionManager() {
		return sessionManager;
	}

	public void setSessionManager(ValidatingSessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public long getInterval() {
		return interval;
	}


	public void setInterval(long interval) {
		this.interval = interval;
	}

	public boolean isEnabled() {
		 return this.enabled;
	}
	
	
	
	public void enableSessionValidation() {

		if(this.interval >1){

            this.service = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setDaemon(true);
                    return thread;
                }
            });
            this.service.scheduleAtFixedRate(this, interval, interval, TimeUnit.MILLISECONDS);
            this.enabled = true;
        
			
		}
		
		
		
	}

	public void run() {
	
		 if (log.isDebugEnabled()) {
	            log.debug("Executing session validation...");
	        }
		
		 long startTime = System.currentTimeMillis();

		 String sql = "select session from sessions limit ?,?";
         int start = 0; //起始记录
         int size = 20; //每页大小 
		 
         List<String> sessionList = jdbcTemplate.queryForList(sql, String.class, start, size);
         
         while(sessionList.size() > 0) {
        	 

             for(String sessionStr : sessionList) {
                 try {
                     Session session = com.kang.myShiroSession.tools.SerializableUtils.deserialize(sessionStr);
                     Method validateMethod = ReflectionUtils.findMethod(AbstractValidatingSessionManager.class, "validate", Session.class, SessionKey.class);
                     validateMethod.setAccessible(true);
                     ReflectionUtils.invokeMethod(validateMethod, sessionManager, session, new DefaultSessionKey(session.getId()));
                 } catch (Exception e) {
                     //ignore
                 }
             }
             start = start + size;
             sessionList = jdbcTemplate.queryForList(sql, String.class, start, size);
         
        	 
         }
         
         
         long stopTime = System.currentTimeMillis();
         if (log.isDebugEnabled()) {
             log.debug("Session validation completed successfully in " + (stopTime - startTime) + " milliseconds.");
         }
         
	}
	
	
	public void disableSessionValidation() {
		   this.service.shutdownNow();
	       this.enabled = false;
	}


	
	
	
	
}
