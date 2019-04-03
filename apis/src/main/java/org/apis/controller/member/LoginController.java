package org.apis.controller.member;

import org.apache.commons.lang3.StringUtils;
import org.apis.service.member.MemberService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vo.ResponseMsg;

@RestController
public class LoginController {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/login")
	public ResponseMsg<String> login(String mobile,String password,String lastLoginIp){
		logger.info("用户登录[webapi]开始，传入参数值：mobile:{},password:{},lastLoginIp:{}", mobile,password,lastLoginIp);
		ResponseMsg<String> msg = new ResponseMsg<String>();
		try{
			if(StringUtils.isEmpty(mobile)){
				throw new RuntimeException("手机号为空");
			}
			if(StringUtils.isEmpty(password)){
				throw new RuntimeException("密码为空");
			}
			memberService.login(mobile, password, lastLoginIp);
		}catch(Exception e){
			msg.failure(e.getMessage());
		}
		return msg;
	}
	
}
