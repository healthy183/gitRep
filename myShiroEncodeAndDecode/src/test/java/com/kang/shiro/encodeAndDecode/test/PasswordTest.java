package com.kang.shiro.encodeAndDecode.test;

import junit.framework.Assert;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.junit.Test;


public class PasswordTest extends BaseTest {

	
	//@Test
	public void testIni(){
		//only validated password T_T||
	    //login("classpath:shiro-passwordservice.ini", "SS", "123");
		
		login("classpath:shiro-jdbc-passwordservice.ini", "liu", "123");
	    
	    boolean authenticated = getSubject().isAuthenticated();
	    
	    Assert.assertTrue(authenticated);
		
	}
	
	//@Test
	public void testSalt(){
		//only validate password
		login("classpath:shiro-hashedCredentialsMatcher.ini", "liudd", "123ff");
		
		boolean authenticated = getSubject().isAuthenticated();
		 
		Assert.assertTrue(authenticated);
	}
	
	
	@Test
	public void testSaltJDBC(){

		
		//shiro use apache commons BeanUtils defaulty but no enum converter ,so you should  DIY!
		 BeanUtilsBean.getInstance().getConvertUtils().register(new EnumConverter(), JdbcRealm.SaltStyle.class);

		 login("classpath:shiro-jdbc-hashedCredentialsMatcher.ini", "liu", "123");
		
	}
	
	private class EnumConverter extends AbstractConverter{

		@Override
		protected Object convertToType(final Class type, final Object value)
				throws Throwable {
			return	Enum.valueOf(type,value.toString());
		}
		
		 @Override
        protected String convertToString(final Object value) throws Throwable {
            return ((Enum) value).name();
        }

		@Override
		protected Class getDefaultType() {
			return null;
		}
		
	}

	
	
}
