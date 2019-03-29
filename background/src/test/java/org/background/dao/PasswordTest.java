package org.background.dao;

import org.apache.shiro.crypto.hash.SimpleHash;

public class PasswordTest {
 public static void main(String[] args) {
	 SimpleHash simpleHash = new SimpleHash("MD5", "admin", "admin", 2);
	 System.out.println(simpleHash.toString());
}
}
