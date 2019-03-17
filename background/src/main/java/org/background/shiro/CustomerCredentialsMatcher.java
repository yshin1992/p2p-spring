package org.background.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义凭证匹配器:主要用于把输入的明文密码加密之后和数据库中的加密密码进行比较
 * 密码加密方式:md5(plainText)+salt)
 * @author yanshuai
 */
public class CustomerCredentialsMatcher extends HashedCredentialsMatcher {
	private Logger logger=LoggerFactory.getLogger(getClass());
	public CustomerCredentialsMatcher(){
		super(Md5Hash.ALGORITHM_NAME);
		logger.debug("初始化");
	}
	/***
	 * 密码匹配:比较
	 * @param token 用户输入提交的令牌
	 * @param info  存储在系统中的令牌
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		if(token==null) throw new UnknownAccountException("没有输入账号信息");
		if(info==null)  throw new UnknownAccountException("存储中没找到账号信息");
		
		String inPassword="";//缺省为没有输入密码
		if(token.getCredentials()!=null){
			inPassword=new String((char[])token.getCredentials()); //输入的明文密码
		}
		SimpleAuthenticationInfo uInfo=(SimpleAuthenticationInfo)info;//用户在数据库中的信息(包括身份、证明、盐值)
		ByteSource salt=uInfo.getCredentialsSalt(); 				  //用户存储的盐值		
		String saltstr=(salt!=null) ? new String(salt.getBytes()):""; //盐值转换为原始的字符串
		
		String ouPassword=new Md5Hash(new Md5Hash(inPassword).toString()+saltstr).toString();//输入的密码进行加密之后的新密码
		logger.debug("加密密码:[{}],原始密码:[{}]",ouPassword,uInfo.getCredentials());
		return ouPassword.equals(uInfo.getCredentials());
	}
}
