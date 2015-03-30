package com.kang.shiro.bitShiroAuthorization;

import java.util.Arrays;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

public class AuthorizerNoConfigTest {

	@Test
	public void mainTest() {

		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		
		//set  authenticator 认证者
		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
		
		authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());

		securityManager.setAuthenticator(authenticator);
		
		//set authorizer 授权人
		ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
		
		authorizer.setPermissionResolver(new WildcardPermissionResolver());
		
		securityManager.setAuthorizer(authorizer);
		
		//set Realm
		DruidDataSource druidDataSource = new DruidDataSource();

		druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		druidDataSource.setUrl("jdbc:mysql://localhost:3306/shiro");
		druidDataSource.setUsername("root");
		druidDataSource.setPassword("Qq123456");
		
		JdbcRealm jdbcRealm = new JdbcRealm();
		jdbcRealm.setDataSource(druidDataSource);
		jdbcRealm.setPermissionsLookupEnabled(true);
		
		List realmList = Arrays.asList(jdbcRealm);
		securityManager.setRealms(realmList);
		
		//set SericurityManager as the  global Variable by SecurityUtils
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
		
		subject.login(token);
		
		Boolean  isAuthenticated = subject.isAuthenticated();
		
		Assert.assertTrue(isAuthenticated);
	}

}
