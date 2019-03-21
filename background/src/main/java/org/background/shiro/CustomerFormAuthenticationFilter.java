package org.background.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomerFormAuthenticationFilter extends FormAuthenticationFilter {

	private static final Logger logger = LoggerFactory.getLogger(CustomerFormAuthenticationFilter.class);
	
	public static String CAPTCHA_PARAM="captcha";
	
	private String captchaParam = CAPTCHA_PARAM;

	public String getCaptchaParam() {
		return captchaParam;
	}

	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}
	
	public String getCaptha(ServletRequest request){
		return WebUtils.getCleanParam(request, captchaParam);
	}
	
	/**保存异常的提示信息到请求中,便于前台获取*/
	@Override
	protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
		if(ae!=null) request.setAttribute(getFailureKeyAttribute(), ae.getMessage());
	}

	protected CaptchaUsernamePasswordToken createToken(ServletRequest request,
			ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		boolean remeberMe = isRememberMe(request);
		String captcha = getCaptha(request);
		String host = getHost(request);	
		return new CaptchaUsernamePasswordToken(username, password, remeberMe, host, captcha);
	}

	/**
	 * 执行登录流程
	 */
	@Override
	protected boolean executeLogin(ServletRequest request,
			ServletResponse response) throws Exception {
		CaptchaUsernamePasswordToken token=createToken(request, response);
		logger.error("Token = {}",token);
		
		
		return super.executeLogin(request, response);
	}
	
	
}
