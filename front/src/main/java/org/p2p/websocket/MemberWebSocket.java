package org.p2p.websocket;

import org.p2p.cache.SessionCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * https://www.cnblogs.com/zzw-blog/p/8530083.html
 */
public class MemberWebSocket implements WebSocketHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        logger.error("链接建立完成:{}",webSocketSession.getId());
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        logger.error("{}-->{}",webSocketSession.getId(),webSocketMessage.getPayload().toString());
        SessionCache.putSocketSession(webSocketMessage.getPayload().toString(),webSocketSession);

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        logger.error("链接关闭:{}",webSocketSession.getId());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
