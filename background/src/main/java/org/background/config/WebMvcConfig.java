package org.background.config;

import org.background.interceptor.ReLoginForbiddenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//注册拦截器，防止用户重复登录
		registry.addInterceptor(new ReLoginForbiddenInterceptor()).addPathPatterns("/login*");
		
		super.addInterceptors(registry);
	}
	
}
