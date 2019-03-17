package org.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {
	
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
