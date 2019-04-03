package org.p2p.listener.event;

import org.springframework.context.ApplicationEvent;

public class MemberRepeatLoginEvent extends ApplicationEvent {

    /**
     * 已经登录过的ID信息（sessionID:memberId)
     */
    private String existedId;

    /**
     * 重复登录的sessionId
     */
    private String repeatSessionId;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MemberRepeatLoginEvent(String existedId,String repeatSessionId) {
        super(existedId);
        this.existedId = existedId;
        this.repeatSessionId = repeatSessionId;
    }

    public String getExistedId() {
        return existedId;
    }

    public String getRepeatSessionId() {
        return repeatSessionId;
    }
}
