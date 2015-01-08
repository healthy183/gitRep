package com.kang.shiro.encodeAndDecode.retry;

import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

public class RetryLimitHashedCredentialsMatcher 
		extends HashedCredentialsMatcher {
	
	private Ehcache passwordRetryCache;
	
	public RetryLimitHashedCredentialsMatcher(){
		
		URL url = CacheManager.class.getClassLoader()
				.getResource("ehcache.xml");
		
		CacheManager cacheManager =  
				CacheManager.newInstance(url);
	
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	
	}

	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info){
		
		String username = (String)token.getPrincipal();
		
		Element element = passwordRetryCache.get(username);
		
		if(element == null){
			
			element = new Element(username,new AtomicInteger(0));
			
			passwordRetryCache.put(element);
		}
		
		AtomicInteger retryCount =	(AtomicInteger) element.getObjectValue();
		
		int  retryTime = retryCount.incrementAndGet();
		
		if(retryTime > 2 ){
			
			  //if retry time > 5 throw
			throw new ExcessiveAttemptsException();
		}
		
		boolean matches = super.doCredentialsMatch(token, info);
		
		if(matches){

			passwordRetryCache.remove(username);
		}
		
		
		return matches;
	}
	
	
	

}
