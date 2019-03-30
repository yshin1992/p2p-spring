package org.p2p.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class ServletInitListener implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(ServletInitListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.error("系统开始销毁。。。。");
		
		logger.error("系统销毁完成。。。");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.error("系统开始启动。。。。");
		String contextPath = sce.getServletContext().getContextPath();
		sce.getServletContext().setAttribute("webRoot", contextPath);
		logger.info("设置系统根路径{}成功",contextPath);
		
		logger.error("系统启动完成。。。");
	}

}
