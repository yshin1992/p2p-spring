package org.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class StringUtil {
	
	public static ObjectMapper mapper = new ObjectMapper();
	
	 static
	 {
	    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	    mapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
	    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, Boolean.TRUE.booleanValue());
	  }
	
	public static String getMD5(String str){
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.reset();
			digest.update(str.getBytes());
			byte[] arr = digest.digest();
			
			StringBuilder digestBuilder = new StringBuilder();
			for(int i=0;i<arr.length;i++){
				if(Integer.toHexString(0xFF & arr[i]).length() ==1){
					digestBuilder.append("0").append(Integer.toHexString(0xFF & arr[i]));
				}else{
					digestBuilder.append(Integer.toHexString(0xFF & arr[i]));
				}
			}
			return digestBuilder.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
}
