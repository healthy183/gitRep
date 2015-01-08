package com.kang.shiro.myShiroRealmJDBC.tool;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.kang.shiro.myShiroRealmJDBC.model.UserModel;

public class PasswordHelper {
	
	
	  private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	 
	  private String algorithmName = "md5";
	  private final int hashIterations = 2;
	  
	  
	  public void encryptPassword(UserModel user){
		  
		  user.setUserSalt(randomNumberGenerator.nextBytes().toHex());
		  
		  String password = user.getUserPassword();
		  
		  ByteSource  byteSource = ByteSource.Util.bytes(user.getCreadentialsSalt());
		 
		  //String encode = byteSource.toHex();
	  
		  String newPassword = new SimpleHash
				  (algorithmName,password,byteSource,hashIterations).toHex();
	  
	 
		  user.setUserPassword(newPassword);
	  
	  }
	  
	
	

}
