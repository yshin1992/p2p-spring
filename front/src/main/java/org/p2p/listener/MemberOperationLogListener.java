package org.p2p.listener;

import org.domain.member.MemberOperationLog;
import org.p2p.listener.event.MemberOperationLogEvent;
import org.p2p.service.member.MemberOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MemberOperationLogListener implements ApplicationListener<MemberOperationLogEvent> {

	@Autowired
	private MemberOperationLogService memberOperationLogService;
	
	@Override
	public void onApplicationEvent(MemberOperationLogEvent event) {
		MemberOperationLog log = (MemberOperationLog) event.getSource();
		memberOperationLogService.save(log);
	}

}
