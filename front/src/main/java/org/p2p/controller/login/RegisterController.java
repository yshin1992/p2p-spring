package org.p2p.controller.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.p2p.constants.Const;
import org.p2p.form.RegisterForm;
import org.p2p.form.group.RegistCheck;
import org.p2p.form.group.RegistSendCodeCheck;
import org.p2p.util.JsonUtil;
import org.p2p.util.RemoteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.vo.ResponseMsg;

@Controller
public class RegisterController {
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@RequestMapping(value="/registerNotice",method=RequestMethod.GET)
	public String registerNotice(){
		return "registerNotice";
	}
	
	@RequestMapping(value="/personalRegist",method=RequestMethod.GET)
	public String personalRegist(ModelMap model){
		RegisterForm form = new RegisterForm();//注册测试用
		form.setNickName("lion1992");
		form.setPhone("15852087981");
		form.setAddress("秦淮区中山南路342号");
		form.setEmail("1658922720@qq.com");
		form.setPassword("1qazxsw2");
		form.setConfirm("1qazxsw2");
		model.addAttribute("user", form);
		return "login/personal_regist";
	}
	
	@RequestMapping(value="/personalRegist",method=RequestMethod.POST)
	public ModelAndView personalRegist(@Validated(value=RegistCheck.class) RegisterForm form,BindingResult bindingResult,HttpSession session){
		ModelAndView mv = new ModelAndView("result");
		if(bindingResult.hasErrors()){
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			mv.setViewName("login/personal_regist");
			mv.addObject("error", fieldErrors);
			return new ModelAndView("login/personal_regist");
		}
		logger.error("注册表单{}",form);
		String nickName = form.getNickName();
		String password = form.getPassword();
		String confirm = form.getConfirm();
		String phone =form.getPhone();
		String address =form.getAddress();
		String email = form.getEmail();
		String captcha = form.getCaptcha();
		String vfCode = form.getVfCode();
		
		if(!StringUtils.equalsIgnoreCase(password, confirm)){
			mv.addObject("error", "两次密码输入不一致");
			return mv;
		}
		//校验图形验证码
		if(!captcha.equalsIgnoreCase((String)session.getAttribute(Const.SESSION_VFCODE))){
			mv.addObject("error", "图形验证码错误");
			return mv;
		}
		
		//校验短信验证码
		Map<String, String> param = new HashMap<String, String>();
		param.put("mobile", phone);
		param.put("mCheckCode", vfCode);
		String vfMsg = RemoteUtil.get("vfCodeValidation", param);
		ResponseMsg<?> msg = JsonUtil.toBeanOrMap(vfMsg, ResponseMsg.class);
		if(msg.getCode()!= ResponseMsg.SUCCESS_CODE.intValue()){
			mv.addObject("error", msg.getMsg());
			return mv;
		}
		
		//开始注册流程
		Map<String,String> params = new HashMap<String,String>();
		params.put("nickName", nickName);
		params.put("password", password);
		params.put("phone", phone);
		params.put("address", address);
		params.put("email", email);
		params.put("vfCode", vfCode);
		
		String result = RemoteUtil.post("register", params);
		ResponseMsg<?> vo = JsonUtil.toBeanOrMap(result, ResponseMsg.class);
		if (vo.getCode() != 200) {
			mv.addObject("error", vo.getMsg());
			mv.addObject("user", form);
			mv.setViewName("login/personal_regist");
			return mv;
		} else {
			mv.addObject("result", "注册成功！");
		}
		return mv;
	}
	
	
	@RequestMapping(value="/enterpriseRegist",method=RequestMethod.GET)
	public String enterpriseRegist(){
		return "login/enterprise_regist";
	}
	
	/**
	 * 申请发送手机验证码
	 * @param form
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value="/sendCode",method=RequestMethod.POST)
	@ResponseBody
	public ResponseMsg<String> sendReigstCode(@Validated(value={RegistSendCodeCheck.class}) RegisterForm form,BindingResult bindingResult,HttpServletRequest request){
		ResponseMsg<String> respMsg = new ResponseMsg<String>();
		if(bindingResult.hasErrors()){
			respMsg.failure(bindingResult.getFieldErrors().get(0).getDefaultMessage());
			return respMsg;
		}
		
		String password = form.getPassword();
		String confirm = form.getConfirm();
		String captcha = form.getCaptcha();
		
		if(!StringUtils.equalsIgnoreCase(password, confirm)){
			respMsg.failure( "两次密码输入不一致");
			return respMsg;
		}
		//校验图形验证码
		if(!captcha.equalsIgnoreCase((String)request.getSession().getAttribute(Const.SESSION_VFCODE))){
			respMsg.failure("图形验证码错误");
			return respMsg;
		}
		
		
		try {
			Map<String,String> params = new HashMap<String,String>();
			params.put("mobile", form.getPhone());
			params.put("module", Const.REGISTER_SMSCODE);
			params.put("visitIp", getRemoteAddress(request));
			
			String result = RemoteUtil.post("getVfCode", params);
			ResponseMsg<?> msg = JsonUtil.toBeanOrMap(result, ResponseMsg.class);
			if(msg.getCode() != ResponseMsg.SUCCESS_CODE.intValue()){
				respMsg.failure(msg.getMsg());
			}
		} catch (Exception e) {
			respMsg.failure("发送短信验证码异常!");
			logger.error("获取短信验证码异常，{}",e);
		}
		
		return respMsg;
	}
	
	private String getRemoteAddress(HttpServletRequest request) throws Exception {  
        String ip = request.getHeader("x-forwarded-for");  
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
    }
}
