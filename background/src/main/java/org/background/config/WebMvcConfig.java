package org.background.config;

import org.background.interceptor.ReLoginForbiddenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//注册拦截器，防止用户重复登录
		registry.addInterceptor(new ReLoginForbiddenInterceptor()).addPathPatterns("/login*");
		
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
