package org.apis.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.apis.config.CacheConfig;
import org.apis.config.ContextConfig;
import org.apis.config.DataSourceConfig;
import org.apis.config.WebMVCConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.Log4jConfigListener;

/**
 * 使用此文件作为web.xml的替代
 * @author yanshuai
 *
 */
public class WebInitializer implements WebApplicationInitializer {

	private static final Logger logger = LoggerFactory.getLogger(WebInitializer.class);
	
	@SuppressWarnings("deprecation")
	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		logger.error("系统开始启动...");
		
		//注册log4j日志管理
		servletContext.addListener(new Log4jConfigListener());
		servletContext.setInitParameter("log4jConfigLocation", "classpath:log4j.xml");
		
		servletContext.setInitParameter("webAppRootKey", "Apis");
		
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(WebMVCConfig.class,DataSourceConfig.class,ContextConfig.class,CacheConfig.class);
		
		//添加字符串编码过滤器
		servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter("UTF-8", true));
		
		//注册dispatcherServlet
		Dynamic dispatcherServlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		dispatcherServlet.addMapping("/");
		dispatcherServlet.setLoadOnStartup(1);
		
		
		
		logger.error("系统启动完成...");
	}

}
