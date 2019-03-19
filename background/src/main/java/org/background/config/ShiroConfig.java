package org.background.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 参考https://412887952-qq-com.iteye.com/blog/2299777
 * Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。 
既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，所以我们需要定义一系列关于URL的规则和访问权限
 * @author yanshuai
 *
 */
//@Configuration//声明这是一个配置文件
public class ShiroConfig {

	@Bean
	public SecurityManager securityManager(){
		return null;
	}
	
	/** 
     * ShiroFilterFactoryBean 处理拦截资源文件问题。 
     * 注意：单独一个ShiroFilterFactoryBean配置是会报错的，因为在 
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager 
     * 
        Filter Chain定义说明 
       1、一个URL可以配置多个Filter，使用逗号分隔 
       2、当设置多个过滤器时，全部验证通过，才视为通过 
       3、部分过滤器可指定参数，如perms，roles 
     * 
     */  
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		//设置SecurityManager
		bean.setSecurityManager(securityManager);
		
		//拦截器
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/", "authc");
		filterChainDefinitionMap.put("/login.html*", "authc");
		filterChainDefinitionMap.put("/favicon.ico", "anno");
		filterChainDefinitionMap.put("/static/**", "anno");
		filterChainDefinitionMap.put("/logout.html*", "logout");
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		bean.setLoginUrl("/login");
		bean.setSuccessUrl("/index");
		bean.setUnauthorizedUrl("/403");
		
		return bean;
	}
	
	
}
