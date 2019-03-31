package org.apis.controller.member;

import org.apis.service.member.MemberService;
import org.domain.member.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vo.ResponseMsg;

@RestController
public class RegisterController {

	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/personalregister")
	public ResponseMsg<String> personalRegister(Member member,String vfCode){
		logger.error("个人注册信息:{},{},{},{},{}",member.getNickName(),member.getPhone(),member.getPassword(),member.getEmail(),member.getAddress());
		ResponseMsg<String> respMsg = new ResponseMsg<String>();
		try {
			memberService.regist(member, Member.MEMBER_KIND_PERSONAL);
		} catch (Exception e) {
			respMsg.failure(e.getMessage());
			logger.error("注册失败!{}",e);
		}
		return respMsg;
	}
	
}
