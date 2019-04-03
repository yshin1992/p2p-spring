package org.p2p.interceptor;

import org.p2p.constants.Const;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 防止用户重复登录
 * @author yshin1992
 *
 */
public class ReLoginForbiddenInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(request.getSession().getAttribute(Const.SESSION_LOGIN_USER)!=null){
			if(isAjaxRequest(request)){
				return false;
			}else{
				String webRoot = (String)request.getServletContext().getAttribute("webRoot");
				response.sendRedirect(webRoot+"/index");
				return false;
			}
		}else{
			return true;
		}
		
	}
	
	protected boolean isAjaxRequest(HttpServletRequest request) {
		return (request != null && ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")) || request.getParameter("ajax") != null));
	}

}
