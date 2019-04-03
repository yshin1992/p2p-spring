package org.p2p.listener;

import org.domain.member.Member;
import org.domain.member.MemberOperationLog;
import org.p2p.cache.SessionCache;
import org.p2p.constants.Const;
import org.p2p.listener.event.MemberOperationLogEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Date;


@WebListener
public class SessionStateListener implements HttpSessionListener{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ApplicationContext applicationContext;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.error("会话开始-->{}",se.getSession().getId());
        if(null == applicationContext){
            applicationContext = WebApplicationContextUtils.getWebApplicationContext(se.getSession().getServletContext());
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.error("会话开始销毁-->{}",se.getSession().getId());
        Object attribute = se.getSession().getAttribute(Const.SESSION_LOGIN_USER);
        if(null != attribute){
            Member member = (Member)attribute;
            MemberOperationLog log = new MemberOperationLog();
            log.init();
            log.setTargetObject(Const.TARGET_LOGTIMEOUT);
            log.setOperateDate(new Date());
            log.setOperateResult(Const.OPERATION_ABNORMAL);
            log.setSessionId(se.getSession().getId());
            log.setMember(member);
            applicationContext.publishEvent(new MemberOperationLogEvent(log));

            //清除掉全局SessionCache
            HttpSession httpSession = SessionCache.getHttpSession(se.getSession().getId() + ":" + member.getId());
            if(null != httpSession){
                SessionCache.clearSession(se.getSession().getId() + ":" + member.getId());
            }
        }




    }
}
