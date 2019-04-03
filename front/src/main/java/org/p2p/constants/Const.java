package org.p2p.constants;

public interface Const {
	/**
	 * 注册页面调用发送验证码短信接口参数:before.register.send.mCheckCode
	 * */
	public static final String REGISTER_SMSCODE = "before.register.send.mCheckCode";
	
	/**验证码sessionKEY*/
	public static final String SESSION_VFCODE = com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;
	
	/**session key 当前登录用户*/
	public static final String SESSION_LOGIN_USER = "loginUser";
	
	/** MemberOperationLog target  **/
	public static final String TARGET_LOGIN = "用户登录";
	public static final String TARGET_LOGOUT = "用户登录退出";
	public static final String TARGET_LOGTIMEOUT = "用户会话超时退出";
	
	/** 操作结果 **/
	short OPERATION_SUCCESS = 0;
	short OPERATION_FAILURE = 1;
	short OPERATION_ABNORMAL = 2;
}
