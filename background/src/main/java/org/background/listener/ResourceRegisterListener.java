package org.background.listener;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.annotation.FunctionEx;
import org.annotation.MenuEx;
import org.business.system.ApplicationService;
import org.business.system.ResourceService;
import org.domain.system.Application;
import org.domain.system.Function;
import org.domain.system.Menu;
import org.domain.system.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

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
	
	@Autowired
	private ResourceService resourceService;
	
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
			if("1".equals(autoRegister)){
				AnnotationConfigEmbeddedWebApplicationContext ctx = (AnnotationConfigEmbeddedWebApplicationContext)event.getApplicationContext();
				context = ctx.getServletContext().getContextPath();
				Application app = createApp();
				applicationService.saveOrUpdate(app);
				logger.error("注册系统App完成!");
				
				//注册菜单
				//从Controller上找到所有的带有MenuEx和FunctionEx的方法
				RequestMappingHandlerMapping mapping = ctx.getBean(RequestMappingHandlerMapping.class);
				Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();
				Set<Class<?>> controllers = new HashSet<>();
				for(RequestMappingInfo info : handlerMethods.keySet()){
					HandlerMethod method = handlerMethods.get(info);
					controllers.add(method.getBeanType()); //将所有的controller类添加到集合中
				}
				
				List<Resource> resList = this.findResources(controllers);
				resourceService.save(resList, app);
			}
		}
		
	}
	
	private List<Resource> findResources(Set<Class<?>> controllers){
		List<Resource> resList = new ArrayList<>();
		for(Class<?> clazz : controllers){
			logger.error("Controller :{}"+clazz.getName());
			//获取类上的RequestMapping注解
			RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
			String urlPath = "";
			if(requestMapping != null && requestMapping.value().length > 0){
				urlPath = requestMapping.value()[0];
			}
			
			//获取类上的菜单信息，默认类上的是顶级菜单
			MenuEx menuParent = clazz.getAnnotation(MenuEx.class);
			if(null != menuParent){
				Menu menu = new Menu(menuParent);
				if(StringUtils.isEmpty(menuParent.appCd())){
					menu.setAppCd(appCd);
				}
				if(StringUtils.isEmpty(menuParent.link())){
					menu.setResourceLink(urlPath);
				}
				logger.error("发现顶级菜单{}",menu);
				resList.add(menu);
			}
			
			//遍历出类中所有方法的注解
			Method[] methods = clazz.getMethods();
			if(!ObjectUtils.isEmpty(methods)){
				for(Method m:methods){
					//查找RequestMapping上的URL
					RequestMapping mapping = m.getAnnotation(RequestMapping.class);
					String urlLink = "";
					if(mapping != null && mapping.value().length>0){
						urlLink = mapping.value()[0];
					}
					//查找方法注解FunctionEx
					FunctionEx functionEx = m.getAnnotation(FunctionEx.class);
					if(null != functionEx){
						Function function = new Function(functionEx,menuParent);
						//如果注解本身没有设置link则去RequestMap上的链接
						if(StringUtils.isEmpty(functionEx.appCd())){
							function.setAppCd(appCd);
						}
						if(StringUtils.isEmpty(functionEx.link())){
							function.setResourceLink(urlPath+urlLink);
						}
						logger.error("查找到方法FunctionEx{}",function);
						resList.add(function);
					}
					//查找方法上的Menu
					MenuEx menuEx = m.getAnnotation(MenuEx.class);
					if(null != menuEx){
						Menu childMenu = new Menu(menuEx);
						if(StringUtils.isEmpty(menuEx.appCd())){
							childMenu.setAppCd(appCd);
						}
						if(StringUtils.isEmpty(menuEx.link())){
							childMenu.setResourceLink(urlPath + urlLink);
						}
						resList.add(childMenu);
					}
					
				}
			}
		}
		return resList;
	}

}
