package org.background.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.background.shiro.CustomerFormAuthenticationFilter;
import org.background.shiro.CustomerRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 参考https://412887952-qq-com.iteye.com/blog/2299777
 * Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。 
既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，所以我们需要定义一系列关于URL的规则和访问权限
 * @author yanshuai
 *
 */
@Configuration//声明这是一个配置文件
@ComponentScan(basePackages={"org.background.shiro"})
public class ShiroConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);
	@Bean
	public SecurityManager securityManager(CustomerRealm customerRealm){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		System.out.println("初始化Shiro -->" + customerRealm);
		securityManager.setRealm(customerRealm);
		return securityManager;
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
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		//设置SecurityManager
		bean.setSecurityManager(securityManager);
		
		Map<String, Filter> filters = new HashMap<String,Filter>();
		filters.put("authc",formAuthenticationFilter());
		bean.setFilters(filters);
		//拦截器
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
		filterChainDefinitionMap.put("/login*", "authc");//这里必须是login*，如果是login.html则是login.html*因为后面会有jsessionID之类的后缀，会影响到URL匹配到这个过滤器上
		filterChainDefinitionMap.put("/", "authc");
		filterChainDefinitionMap.put("/favicon.ico", "anon");
		filterChainDefinitionMap.put("/login/captcha.png*", "anon");
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/**", "user");
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		bean.setLoginUrl("/login");
		bean.setSuccessUrl("/index");
		bean.setUnauthorizedUrl("/403");
		
		logger.error("Shiro 初始化完成...");
		return bean;
	}
	
	@Bean
	public FormAuthenticationFilter formAuthenticationFilter(){
		FormAuthenticationFilter filter = new CustomerFormAuthenticationFilter();
		filter.setSuccessUrl("/index");
		return filter;
	}

	/**
     * 负责shiroBean的生命周期
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    
    /**
     * 开启Shiro注解支持
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
    	AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    	advisor.setSecurityManager(securityManager);
    	return advisor;
    }
    
    /**
     * AOP支持以类作为代理
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
    	DefaultAdvisorAutoProxyCreator aop = new DefaultAdvisorAutoProxyCreator();
    	aop.setProxyTargetClass(true);
    	return aop;
    }
}
