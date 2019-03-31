package org.p2p.controller.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.p2p.form.RegisterForm;
import org.p2p.form.group.LoginCheck;
import org.p2p.util.JsonUtil;
import org.p2p.util.RemoteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.vo.ResponseMsg;

@Controller
public class RegisterController {
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@RequestMapping(value="/personalRegist",method=RequestMethod.GET)
	public String personalRegist(){
		return "login/personal_regist";
	}
	
	@RequestMapping(value="/personalRegist",method=RequestMethod.POST)
	public ModelAndView personalRegist(@Validated(value=LoginCheck.class) RegisterForm form,BindingResult bindingResult ){
		ModelAndView mv = new ModelAndView("login/regist_result");
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
		String vfCode = form.getVfCode();
		
		if(!StringUtils.equalsIgnoreCase(password, confirm)){
			mv.addObject("error", "两次密码输入不一致");
			return mv;
		}
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("nickName", nickName);
		params.put("password", password);
		params.put("phone", phone);
		params.put("address", address);
		params.put("email", email);
		params.put("vfCode", vfCode);
		
		String result = RemoteUtil.post("register", params);
		@SuppressWarnings("unchecked")
		ResponseMsg<String> vo = JsonUtil.toBeanOrMap(result, ResponseMsg.class);
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
	
}
