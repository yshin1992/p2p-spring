package org.p2p.constants;

public final class Const {
	/**
	 * 注册页面调用发送验证码短信接口参数:before.register.send.mCheckCode
	 * */
	public static final String REGISTER_SMSCODE = "before.register.send.mCheckCode";
	
	/**验证码sessionKEY*/
	public static final String SESSION_VFCODE = com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;
	
	/**session key 当前登录用户*/
	public static final String SESSION_LOGIN_USER = "loginUser";
}
