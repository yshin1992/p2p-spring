package org.background.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.business.util.SysFuncCache;
import org.domain.system.Resource;
import org.domain.system.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 这个拦截器是用于将登录过后的系统操作人员的访问路径放在request域中
 */
public class URLRequestInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private SysFuncCache sysFuncCache;
	
	public void setSysFuncCache(SysFuncCache sysFuncCache){
		this.sysFuncCache = sysFuncCache;
	}

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	String contextPath = request.getServletContext().getContextPath();
        String url = request.getRequestURI().replace(contextPath, "");
        logger.error("URL {}",url);
        Object attr = request.getSession().getAttribute("curUser");
        if(attr != null){
        	User user = (User)attr;
        	List<Resource> resourcePath = sysFuncCache.getResourcePath(url, user.getUserCd());
        	if(resourcePath != null && !resourcePath.isEmpty()){
        		StringBuilder navs = new StringBuilder();
            	for(Resource r : resourcePath){
            		navs.append(r.getResourceNm()).append(",");
            	}
            	navs.deleteCharAt(navs.length()-1);
            	//添加导航栏信息到request域中
            	request.setAttribute("navs", navs);
            	
            	List<Resource> childPath = sysFuncCache.getChildResource(resourcePath.get(resourcePath.size()-1), user.getUserCd());
            	if(null != childPath && !childPath.isEmpty()){
            		request.setAttribute("resources", childPath);
            	}
        	}
        	
        }
        
        return super.preHandle(request, response, handler);
    }
}
