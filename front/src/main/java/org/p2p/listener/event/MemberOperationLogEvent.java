package org.p2p.listener.event;

import org.domain.member.MemberOperationLog;
import org.springframework.context.ApplicationEvent;

public class MemberOperationLogEvent extends ApplicationEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2490991254715641003L;

	public MemberOperationLogEvent(MemberOperationLog source) {
		super(source);
	}

}
