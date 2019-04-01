package org.p2p.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.domain.member.Member;
import org.p2p.constants.Const;
import org.p2p.form.RegisterForm;
import org.p2p.form.group.LoginCheck;
import org.p2p.util.JsonUtil;
import org.p2p.util.RemoteUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.vo.ResponseMsg;

@Controller
public class LoginController {

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "login/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(@Validated(value=LoginCheck.class) RegisterForm form,BindingResult bindingResult,HttpServletRequest request){
		ModelAndView mv = new ModelAndView("result");
		if(bindingResult.hasErrors()){
			mv.addObject("error",bindingResult.getFieldErrors().get(0).getDefaultMessage());
			mv.setViewName("login/login");
			return mv;
		}
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("mobile", form.getPhone());
		params.put("password", form.getPassword());
		params.put("lastLoginIp",  request.getRemoteAddr());
		
		String jsonStr = RemoteUtil.post("login", params);
		@SuppressWarnings("unchecked")
		ResponseMsg<Member> msg = JsonUtil.toBeanOrMap(jsonStr, ResponseMsg.class);
		if(msg.getCode() != ResponseMsg.SUCCESS_CODE.intValue()){
			mv.addObject("error",msg.getMsg());
			mv.setViewName("login/login");
		}else{
			Member member = msg.getData();
			request.getSession().setAttribute(Const.SESSION_LOGIN_USER, member);
			mv.addObject("result", "登录成功");
		}
		
		return mv;
	}
	
}
