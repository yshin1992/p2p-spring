package org.background.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.business.system.ApplicationService;
import org.domain.system.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;

/**
 * 系统初始化Listener
 * @author yshin1992
 *
 */
@PropertySource(value="classpath:development/app.properties")
@Component
public class InitServletContextListener implements WebApplicationInitializer,ApplicationListener<ApplicationContextEvent> {

	private static final Logger logger = LoggerFactory.getLogger(InitServletContextListener.class);

	@Value("${appCd}")
	private String appCd;
	@Value("${appNm}")
	private String appNm;
	@Value("${appDesc}")
	private String appDesc;
	@Value("${app.host}")
	private String host;
	@Value("${app.port}")
	private String port;
	@Value("${autoRegister}")
	private String autoRegister="0";
	
	private String context="/";
	
	@Autowired
	private ApplicationService applicationService;
	
	private Application createApp(){
		Application app = new Application();
		app.setAppCd(appCd);
		app.setAppName(appNm);
		app.setAppDesc(appDesc);
		app.setHost(host);
		app.setPort(port);
		app.setContext(context);
		app.init();
		return app;
	}
	
	@Override
	public void onApplicationEvent(ApplicationContextEvent event) {
		//先注册应用程序
		if(event instanceof ContextRefreshedEvent){
			if(event.getApplicationContext().getParent() != null){
				if("1".equals(autoRegister)){
					Application app = createApp();
					logger.error("开始注册应用程序{}。。。",app);
					applicationService.saveOrUpdate(app);
					logger.error("应用程序注册完成。。。");
				}
			}
		}
	}

	@Override
	public void onStartup(ServletContext sc) throws ServletException {
		logger.error("系统开始启动");
		context = sc.getContextPath();
		logger.error("系统路径 :"+context);
		sc.setAttribute("webRoot", context);
	}
	

}
