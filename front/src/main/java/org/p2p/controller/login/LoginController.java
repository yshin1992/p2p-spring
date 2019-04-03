package org.p2p.controller.login;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.domain.member.Member;
import org.domain.member.MemberOperationLog;
import org.p2p.cache.SessionCache;
import org.p2p.constants.Const;
import org.p2p.ext.LoginedRequired;
import org.p2p.form.RegisterForm;
import org.p2p.form.group.LoginCheck;
import org.p2p.listener.event.MemberOperationLogEvent;
import org.p2p.listener.event.MemberRepeatLoginEvent;
import org.p2p.service.member.MemberService;
import org.p2p.util.JsonUtil;
import org.p2p.util.RemoteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.vo.ResponseMsg;

@Controller
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ApplicationContext applicationContext;
	
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
		ResponseMsg<?> msg = JsonUtil.toBeanOrMap(jsonStr, ResponseMsg.class);
		if(msg.getCode() != ResponseMsg.SUCCESS_CODE.intValue()){
			mv.addObject("error",msg.getMsg());
			mv.setViewName("login/login");
		}else{
			Member member = memberService.queryMemberByPhone(form.getPhone());
			request.getSession().setAttribute(Const.SESSION_LOGIN_USER, member);
			mv.addObject("result", "登录成功");
			//这里做一下校验，看用户之前是否在别处登录着
			checkRepeatLogin(member.getMemberId(),request.getSession().getId());
			//保存到全局SessionCache中
			SessionCache.putHttpSession(request.getSession().getId()+":"+member.getId(),request.getSession());
			//记录用户登录日志
			publishOperationLog(request,Const.TARGET_LOGIN,member);

		}
		return mv;
	}

	@LoginedRequired
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session,HttpServletRequest request){
		//记录用户登录退出日志
		Member member = (Member)session.getAttribute(Const.SESSION_LOGIN_USER);
		publishOperationLog(request,Const.TARGET_LOGOUT,member);
		SessionCache.clearSession(request.getSession().getId()+":"+member.getId());
		return "redirect:/";
	}

	private void publishOperationLog(HttpServletRequest request,String targetObject,Member member){
		MemberOperationLog log = new MemberOperationLog(request);
		log.init();
		log.setTargetObject(targetObject);
		log.setOperateDate(new Date());
		log.setOperateResult(Const.OPERATION_SUCCESS);
		log.setSessionId(request.getSession().getId());
		log.setIp(request.getRemoteAddr());
		log.setUrl(request.getRequestURL().toString());
		log.setMember(member);
		applicationContext.publishEvent(new MemberOperationLogEvent(log));
	}

	private void checkRepeatLogin(String memberId,String currentSessionId){
		String existId = SessionCache.checkExist(memberId);
		if(null != existId){
			logger.error("当前用户{}已在某处登录过",memberId);
			applicationContext.publishEvent(new MemberRepeatLoginEvent(existId,currentSessionId));
		}
	}

}
