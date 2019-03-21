package org.background.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

public class CustomerFormAuthenticationFilter extends FormAuthenticationFilter {

	private static final Logger logger = LoggerFactory
			.getLogger(CustomerFormAuthenticationFilter.class);

	public static String CAPTCHA_PARAM = "captcha";

	private String captchaParam = CAPTCHA_PARAM;

	public String getCaptchaParam() {
		return captchaParam;
	}

	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}

	public String getCaptha(ServletRequest request) {
		return WebUtils.getCleanParam(request, captchaParam);
	}

	/** 保存异常的提示信息到请求中,便于前台获取 */
	@Override
	protected void setFailureAttribute(ServletRequest request,
			AuthenticationException ae) {
		if (ae != null)
			request.setAttribute(getFailureKeyAttribute(), ae.getMessage());
	}

	protected CaptchaUsernamePasswordToken createToken(ServletRequest request,
			ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		boolean remeberMe = isRememberMe(request);
		String captcha = getCaptha(request);
		String host = getHost(request);
		return new CaptchaUsernamePasswordToken(username, password, remeberMe,
				host, captcha);
	}

	/**
	 * 执行登录流程
	 */
	@Override
	protected boolean executeLogin(ServletRequest request,
			ServletResponse response) throws Exception {
		CaptchaUsernamePasswordToken token = null;
		try {

			token = createToken(request, response);
			logger.error("Token = {}", token);
			// 验证图形验证码
			doCaptchaValidate(token, request);

			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			logger.info("[{}]登录成功", token.getUsername());
			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {
			logger.info("[{}]登录失败:{}",
					token == null ? "" : token.getUsername(), e.getMessage());
			return onLoginFailure(token, e, request, response);
		}
	}

	private void doCaptchaValidate(CaptchaUsernamePasswordToken token,
			ServletRequest request) {
		String captcha = token.getCaptcha();
		HttpServletRequest req = (HttpServletRequest) request;
		String attribute = (String) req.getSession().getAttribute(
				com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (ObjectUtils.isEmpty(attribute)) {
			throw new UnknownAccountException("未获取到后台的图形验证码信息");
		}

		if (ObjectUtils.isEmpty(captcha)
				|| !captcha.equalsIgnoreCase(attribute)) {
			throw new UnknownAccountException("图形验证码错误!");
		}
		req.getSession().removeAttribute(
				com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
	}

}
