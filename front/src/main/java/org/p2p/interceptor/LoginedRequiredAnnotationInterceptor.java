package org.p2p.interceptor;

import org.p2p.constants.Const;
import org.p2p.ext.LoginedRequired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginedRequiredAnnotationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod hdl = (HandlerMethod)handler;
            //从HandlerMethod 获取 LoginRequired 注解
            LoginedRequired loginedRequired = hdl.getMethodAnnotation(LoginedRequired.class);
            if(loginedRequired != null){
                //loginRequired 不为空，说明此请求必须要求用户登录才能操作
                Object attribute = request.getSession().getAttribute(Const.SESSION_LOGIN_USER);
                if(null == attribute){
                  //用户尚未登录，将页面直接跳转到首页
                    String contextPath = request.getServletContext().getContextPath();
                    response.sendRedirect(contextPath+"/index");
                    return false;
                }
            }
        }

        return super.preHandle(request, response, handler);
    }
}
