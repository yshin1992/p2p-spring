package org.apis.listener.event;

import org.springframework.context.ApplicationEvent;

/**
 * 注册成功事件
 * @author yanshuai
 *
 */
public class RegisterSucessEvent extends ApplicationEvent {

	private static final long serialVersionUID = 7410712882266643669L;

	public RegisterSucessEvent(String memberId) {
		super(memberId);
	}

}
