package org.ysh;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class Md5HashTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Md5Hash(new Md5Hash("pswd").toString() + "salt").toString());
		System.out.println(new SimpleHash("MD5", "admin", "admin",2).toString());
	}

}
