package org.background.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	
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
		return "hello";
	}
}
