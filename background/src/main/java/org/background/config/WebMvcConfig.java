package org.background.config;

import org.background.interceptor.ReLoginForbiddenInterceptor;
import org.background.interceptor.URLRequestInterceptor;
import org.business.util.SysFuncCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	SysFuncCache sysFuncCache;
	
	private static final String[] excludePath = {"/login*","/logout*","/index","/left","/header","/main"};
	
	/**
	 * 配置OpenSessionInView
	 * @return
	 */
	@Bean
	public OpenEntityManagerInViewFilter openEntityManagerInViewFilter(){
	   return new OpenEntityManagerInViewFilter();
	}

	@Bean
	public URLRequestInterceptor urlHandlerInterceptor(){
		URLRequestInterceptor interceptor = new URLRequestInterceptor();
		interceptor.setSysFuncCache(sysFuncCache);
		return interceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//注册拦截器，防止用户重复登录
		registry.addInterceptor(new ReLoginForbiddenInterceptor()).addPathPatterns("/login*");
		registry.addInterceptor(urlHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns(excludePath);
		super.addInterceptors(registry);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//设置首页 
		registry.addViewController("/").setViewName("system/index");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(registry);
	}
	
}
