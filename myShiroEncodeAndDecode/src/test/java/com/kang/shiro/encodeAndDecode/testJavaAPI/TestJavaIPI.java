package com.kang.shiro.encodeAndDecode.testJavaAPI;

import java.security.Key;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.BlowfishCipherService;
import org.apache.shiro.crypto.DefaultBlockCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.Sha384Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Assert;
import org.junit.Test;

public class TestJavaIPI {
	
	@Test
	public void testBase64(){
		
		String str = "hello";
		
		byte[] byteArray = str.getBytes();
		
		String encodeString = Base64.encodeToString((byteArray));
		
		String decodeString = Base64.decodeToString(encodeString);
		
		Assert.assertEquals(str, decodeString);
		
	}
	
	@Test
	public void testHex(){
		
		String str = "hello";
		
		String encodeStr =	Hex.encodeToString(str.getBytes());
		
		String str2 =  new String(Hex.decode(encodeStr.getBytes()));
		
		String str3 = Hex.decode(encodeStr.getBytes()).toString();
		
		Assert.assertEquals(str, str2);
		
	}
	
	@Test
	public void testCodeSupport(){
		
		 String str = "hello";
		 byte[] bytes = CodecSupport.toBytes(str,"utf-8");
		 String codeStr = CodecSupport.toString(bytes);
		 Assert.assertEquals(str, codeStr);
	}
	
	@Test
	public void testRandom(){
		
		SecureRandomNumberGenerator randomNumberGenerator 
			= new SecureRandomNumberGenerator();
		
		 String str = "123";
		
		randomNumberGenerator.setSeed(str.getBytes());
		
		String randomCode = randomNumberGenerator.nextBytes().toHex();
		
		
	}
	
	
	@Test
	public void testMD5Hash(){
		
		//we have many hash arithmetic such as md5 SHA
		//if only encode through md5 ,it is be decoded easily
		//so we ned salt to encode
		String str = "hello";

		String salt = "123";
		
		String md5 = new Md5Hash(str,salt).toString();
		
		//double encode,md5(md5(str)) 
		String md5X2 = new Md5Hash(str,salt,2).toString();
		
		String sha1 = new Sha1Hash(str,salt).toString();
		
		//encode throught Sha256Hash
		String sha256 = new Sha256Hash(str,salt).toString();
		
		String sha384 = new Sha384Hash(str, salt).toString();
		
		String sha3512 = new Sha512Hash(str, salt).toString();
		
		//encode throught Shiro
		String simpleHash = new SimpleHash("SHA-1",str,salt).toString();
		
		
	}
	
	@Test
	public void ShiroHashService(){
		
		DefaultHashService hashService = new DefaultHashService();//default arithmetic is SHA-512
		
		hashService.setHashAlgorithmName("SHA-512");
		
		//set Private Salt
		hashService.setPrivateSalt(new SimpleByteSource("123"));
		
		//is generate public salt? default false
		hashService.setGeneratePublicSalt(true);
		
		SecureRandomNumberGenerator  secureRandomNumberGenerator 
			=	new SecureRandomNumberGenerator();
		//the default generate public salt tool calss:SecureRandomNumberGenerator
		hashService.setRandomNumberGenerator(secureRandomNumberGenerator);
		
		//set Hash Iteration counts
		hashService.setHashIterations(1);
		
		HashRequest request = new HashRequest.Builder()
		.setAlgorithmName("MD5")
			.setSource(ByteSource.Util.bytes("hello"))
		 		.setSalt(ByteSource.Util.bytes("123"))
		 			.setIterations(2).build();
		
		String hex = hashService.computeHash(request).toHex();   
		
		/*HashRequest request = new HashRequest.Builder()  
        .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello"))  
        .setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();  
		String hex = hashService.computeHash(request).toHex();  
		*/
	}
	
	@Test
	public void testEncryptAndDecrypt(){
		
		AesCipherService aesCipherService =	new AesCipherService();
		
		aesCipherService.setKeySize(128);
		
		Key key = aesCipherService.generateNewKey();
		
		String text = "hello";
		
		//encrypt
		byte[] textByteArray = text.getBytes();
		byte[] encodeByteArray = key.getEncoded();
		
		String encrptText = aesCipherService.encrypt
				(textByteArray, encodeByteArray).toHex();
	
		//decrypt
		byte[] decodeArray = Hex.decode(encrptText);
		byte[] decodeByteArray = key.getEncoded();
	
		byte[] decryptByte = aesCipherService.decrypt
				(decodeArray, decodeByteArray).getBytes();
		
		String str = new String(decryptByte);
		
		Assert.assertEquals(text,str);
		
	}
	
	@Test
	public void testBlowfishCipherService() {
		
		BlowfishCipherService blowfishCipherService =
				new BlowfishCipherService(); 
		
		blowfishCipherService.setKeySize(128);

		Key key = blowfishCipherService.generateNewKey();
	
		String text  = "hello";
		
		byte[] byteArray = text.getBytes();
		
		byte[] encodeArray = key.getEncoded();
		
		//encrypt
		String hexStr = blowfishCipherService.encrypt(byteArray, encodeArray).toHex();
		
		//decrypt
		byte[]  decodeStr = Hex.decode(hexStr);
		
		byte[] decryptByteArray = blowfishCipherService.decrypt(decodeStr, encodeArray).getBytes();
		
		String enStr = new String(decryptByteArray);
		
		Assert.assertEquals(text, enStr);
	}
	
	@Test
	public void testDefaultBlockCipherService(){
		
		//对称加密，使用Java的JCA（javax.crypto.Cipher）加密API，常见的如 'AES', 'Blowfish'
		DefaultBlockCipherService cipherService = 
				new DefaultBlockCipherService("AES");
		
		cipherService.setKeySize(128);
		
		Key key = cipherService.generateNewKey();
		
		String text = "hello";
		
		//加密
        //String encrptText = cipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
		//encrypt
        byte[] byteArray =	text.getBytes();
        
        byte[] encodeByteArray = key.getEncoded();
        
        String encryptStr = cipherService.encrypt(byteArray, encodeByteArray).toHex();
        
        
        byte[] decodeByteArray =  Hex.decode(encryptStr);
        
        byte[] decryptByteArray = cipherService.decrypt(decodeByteArray, encodeByteArray).getBytes();
        
        String decryptStr = new String(decryptByteArray);
        
        Assert.assertEquals(text,decryptStr);
        //解密
       //String text2 = new String(cipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());
	}
	
	@Test
	public void testMD5SecureRandomNumberGenerator(){
		
		String algorithmName = "md5"; 
		
		String username = "liu";
		
		String password = "123";
		
		String saltOne = username;
		
		//remeber save saltTwo in db
		//28303b6f23ab60cab00fe9749b21f17d
		String saltTwo = new SecureRandomNumberGenerator().nextBytes().toHex();
		
		int hashIteration = 2;
		
		SimpleHash simpleHash = new SimpleHash
				(algorithmName,password,saltOne+saltTwo,hashIteration);
		
		//3db4346ae090dd01b64af824a7c4befb
		String encodePassword = simpleHash.toHex();
		
	}
	
}
