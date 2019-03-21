package org.background.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2309275664063997156L;

	/**
	 * 图片验证码
	 */
	private String captcha;

	public CaptchaUsernamePasswordToken(String username, char[] password,
			boolean rememberMe, String host,String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}

	public CaptchaUsernamePasswordToken(String username, String password,
			boolean rememberMe, String host,String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
	public void clear() {
		super.clear();
		this.captcha=null;
	}  
	
	
}
