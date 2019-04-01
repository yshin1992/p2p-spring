package org.apis.listener.event;

import org.domain.member.Member;
import org.springframework.context.ApplicationEvent;

public class LoginSuccessEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6930887555950679608L;

	private boolean isFirstLoginToday;
	
	public boolean isFirstLoginToday() {
		return isFirstLoginToday;
	}

	public LoginSuccessEvent(Member member,boolean isFirstLoginToday) {
		super(member);
		this.isFirstLoginToday = isFirstLoginToday;
	}

}
