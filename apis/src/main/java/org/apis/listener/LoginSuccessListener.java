package org.apis.listener;

import org.apis.listener.event.LoginSuccessEvent;
import org.apis.service.member.MemberIntegralService;
import org.domain.member.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessListener implements ApplicationListener<LoginSuccessEvent>{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MemberIntegralService memberIntegralService;
	
	@Override
	public void onApplicationEvent(LoginSuccessEvent event) {
		Member member = (Member)event.getSource();
		logger.debug("会员登录成功后获得积分=====开始,memberId:{}", member.getMemberId());
		try {
			memberIntegralService.loginGiveIntegral(member ,event.isFirstLoginToday());
		} catch (Exception e) {
			logger.error("{}", e);
		}
		logger.debug("会员登录成功后获得积分=====结束");
	}

}
