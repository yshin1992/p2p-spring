package org.background.listener;

import org.business.system.ApplicationService;
import org.domain.system.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;

@PropertySource(value="classpath:development/app.properties")
@Component
public class ResourceRegisterListener implements ApplicationListener<ApplicationContextEvent> {
	
	private static final Logger logger = LoggerFactory.getLogger(ResourceRegisterListener.class);
	
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
		// TODO Auto-generated method stub
		if(event.getApplicationContext() instanceof AnnotationConfigEmbeddedWebApplicationContext){
			AnnotationConfigEmbeddedWebApplicationContext ctx = (AnnotationConfigEmbeddedWebApplicationContext)event.getApplicationContext();
			context = ctx.getServletContext().getContextPath();
			Application app = createApp();
			applicationService.saveOrUpdate(app);
			logger.error("注册系统App完成!");
		}
		
	}

}
