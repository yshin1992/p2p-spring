package org.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class StringUtil {

	public static ObjectMapper mapper = new ObjectMapper();
	public static String[] chars = { "a", "b", "c", "d", "e", "f", "g", "h",
			"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
			"v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7",
			"8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
			"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
			"Y", "Z" };
	static {
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,
				Boolean.TRUE.booleanValue());
	}

	public static String getMD5(String str) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.reset();
			digest.update(str.getBytes());
			byte[] arr = digest.digest();

			StringBuilder digestBuilder = new StringBuilder();
			for (int i = 0; i < arr.length; i++) {
				if (Integer.toHexString(0xFF & arr[i]).length() == 1) {
					digestBuilder.append("0").append(
							Integer.toHexString(0xFF & arr[i]));
				} else {
					digestBuilder.append(Integer.toHexString(0xFF & arr[i]));
				}
			}
			return digestBuilder.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 生成加密的密码
	 * 
	 * @param password
	 * @param salt
	 * @return
	 */
	public static String generateEncryptPassword(String password, String salt) {
		return new SimpleHash("MD5", password, salt, 2).toString();
	}

	/**
	 * 生成短的UUID作为用户的realCd
	 * @return
	 */
	public static String generateShortUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[(x % 62)]);
		}
		return shortBuffer.toString();
	}
}
