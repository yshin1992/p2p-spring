package org.p2p.controller.login;

import org.p2p.form.RegisterForm;
import org.p2p.form.group.LoginCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@RequestMapping(value="/personalRegist",method=RequestMethod.GET)
	public String personalRegist(){
		return "login/personal_regist";
	}
	
	@RequestMapping(value="/personalRegist",method=RequestMethod.POST)
	public ModelAndView personalRegist(@Validated(value=LoginCheck.class) RegisterForm form,BindingResult bindingResult ){
		if(bindingResult.hasErrors()){
			return new ModelAndView("login/personal_regist");
		}
		logger.error("注册表单{}",form);
		ModelAndView mv = new ModelAndView("login/regist_result");
		String nickNm = form.getNickName();
		String password = form.getPassword();
		String confirm = form.getConfirm();
		String phone =form.getPhone();
		String address =form.getAddress();
		String email = form.getEmail();
		String vfCode = form.getVfCode();
		mv.addObject("result", "注册成功！");
		
		return mv;
	}
	
	
	@RequestMapping(value="/enterpriseRegist",method=RequestMethod.GET)
	public String enterpriseRegist(){
		return "login/enterprise_regist";
	}
	
}
