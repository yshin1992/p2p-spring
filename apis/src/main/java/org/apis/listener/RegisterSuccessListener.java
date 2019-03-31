package org.apis.listener;

import org.apis.listener.event.RegisterSucessEvent;
import org.apis.service.member.MemberIntegralService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RegisterSuccessListener implements ApplicationListener<RegisterSucessEvent> {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MemberIntegralService memberIntegralService;
	
	@Override
	public void onApplicationEvent(RegisterSucessEvent event) {
		String memberId = (String)event.getSource();
		logger.error("会员注册成功后获得积分=====开始,memberId:{}", memberId);
		memberIntegralService.initMemberIntegral(memberId);
		memberIntegralService.registerGiveIntegral(memberId);
		logger.error("会员注册成功后获得积分=====结束");
	}

}
