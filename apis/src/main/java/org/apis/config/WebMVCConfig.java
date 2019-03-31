package org.apis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * WebMvc配置文件（相当于Dispatch-servlet.xml)
 * @author yanshuai
 *
 */
@EnableWebMvc//开启WebMVC
@Configuration
@ComponentScan(basePackages={"org.apis.controller"})
public class WebMVCConfig extends WebMvcConfigurerAdapter{
	/**
	 * 配置试图解析器
	 * @return
	 */
	@Bean
	public InternalResourceViewResolver viewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/classes/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}
}
