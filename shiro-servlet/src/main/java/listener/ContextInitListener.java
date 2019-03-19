package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextInitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String contextPath = sce.getServletContext().getContextPath();
		System.out.println(contextPath);
		sce.getServletContext().setAttribute("webRoot", contextPath);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
