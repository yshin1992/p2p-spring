package org.background.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.business.system.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 系统初始化Listener
 * @author yshin1992
 *
 */
@Component
public class InitServletContextListener implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(InitServletContextListener.class);
	
	@Autowired
	private ApplicationService applicationService;

	@Override
	public void contextInitialized(ServletContextEvent sc) {
		// TODO Auto-generated method stub
		logger.error("系统开始启动");
		String context = sc.getServletContext().getContextPath();
		logger.error("系统路径 :"+context);
		sc.getServletContext().setAttribute("webRoot", context);
	}


	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
	

}
