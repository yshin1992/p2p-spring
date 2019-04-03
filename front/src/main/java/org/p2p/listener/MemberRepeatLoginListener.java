package org.p2p.listener;

import net.sf.json.JSONObject;
import org.domain.member.MemberOperationLog;
import org.p2p.cache.SessionCache;
import org.p2p.constants.Const;
import org.p2p.listener.event.MemberRepeatLoginEvent;
import org.p2p.service.member.MemberOperationLogService;
import org.p2p.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.vo.SocketMsg;

import java.io.IOException;
import java.util.Date;

@Component
public class MemberRepeatLoginListener implements ApplicationListener<MemberRepeatLoginEvent> {

    @Autowired
    MemberOperationLogService memberOperationLogService;

    @Autowired
    MemberService memberService;

    @Override
    public void onApplicationEvent(MemberRepeatLoginEvent event) {
       String existId = event.getExistedId();
       String repeatSessionId = event.getRepeatSessionId();
       //从全局缓存中得到socket信息，从socket发送消息提示用户当前账号在异地登录
        WebSocketSession socketSession = SessionCache.getSocketSession(existId);
        try {
            JSONObject jsonObject = JSONObject.fromObject(new SocketMsg(SocketMsg.DATA_TYPE_LOGIN, "当前账户在异地登录!请确认账户是否安全!"));
            socketSession.sendMessage(new TextMessage(jsonObject.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //记录下日志后，从全局缓存中清除掉session信息
        MemberOperationLog log = new MemberOperationLog();
        log.init();
        log.setTargetObject("异常退出:"+repeatSessionId);
        log.setOperateDate(new Date());
        log.setOperateResult(Const.OPERATION_SUCCESS);

        String[] existInfo = existId.split(":");

        log.setSessionId(existInfo[0]);
        log.setMember(memberService.queryMemberById(existInfo[1]));
        memberOperationLogService.save(log);

        SessionCache.clearSession(existId);
    }
}
