package org.background.controller.system;

import java.util.List;

import org.business.system.ResourceService;
import org.domain.system.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "system/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(WebRequest request,ModelMap model){
		Object object = request.getAttribute("shiroLoginFailure",WebRequest.SCOPE_REQUEST);
		if (object != null) {
			model.put("error", "登录提交失败:账号或密码错误");
			return new ModelAndView("system/login", model);
		} else {
			return new ModelAndView("hello");
		}
	}
	
	@RequestMapping(value="/index")
	public String index(){
		return "system/index";
	}
	
	@RequestMapping(value="/left")
	public ModelAndView left(String resourceCd){
		ModelAndView mv = new ModelAndView("system/left");
		if(null != resourceCd){
			Resource resource = resourceService.findResourceByCd(resourceCd);
			if(null != resource)
			{
				List<Resource> childs = resource.getChilds();
				mv.addObject("resources", childs);
			}
		}
		return mv;
	}
	
	@RequestMapping("/header")
	public String header(){
		return "system/header"; 
	}
	
	@RequestMapping("/main")
	public String main(){
		return "system/main";
	}
}
