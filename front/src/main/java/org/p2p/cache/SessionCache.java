package org.p2p.cache;

import org.p2p.constants.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SessionCache {

    private static final Logger logger = LoggerFactory.getLogger(SessionCache.class);

    private static final ConcurrentHashMap<String, HttpSession> HTTP_SESSION_MAP = new ConcurrentHashMap<String, HttpSession>();

    private static final ConcurrentHashMap<String, WebSocketSession> SOCKET_SESSION_MAP = new ConcurrentHashMap<String, WebSocketSession>();

    public static void putHttpSession(String id,HttpSession session){
        HTTP_SESSION_MAP.put(id,session);
    }

    public static HttpSession getHttpSession(String id){
        return HTTP_SESSION_MAP.get(id);
    }

    public static void removeHttpSession(String id){
        HTTP_SESSION_MAP.remove(id);
    }

    public static void putSocketSession(String id,WebSocketSession session){
        SOCKET_SESSION_MAP.put(id,session);
    }

    public static WebSocketSession getSocketSession(String id){
        return SOCKET_SESSION_MAP.get(id);
    }

    public static void removeSocketSession(String id){
        SOCKET_SESSION_MAP.remove(id);
    }

    public static void clearSession(String id){
        HttpSession httpSession = getHttpSession(id);
        //销毁会话信息
        if(httpSession != null){
            logger.error("清除HttpSession [{}]",httpSession.getId());
            httpSession.removeAttribute(Const.SESSION_LOGIN_USER);
            httpSession.invalidate();
        }
        removeHttpSession(id);
        //关闭WebSocket
        WebSocketSession socketSession = getSocketSession(id);
        logger.error("关闭websocket [{}]",socketSession.getId());
        if(null != socketSession){
            try {
                socketSession.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        removeSocketSession(id);
    }

    /**
     * 校验用户是否已经登录过
     * @param memberId
     * @return
     */
    public static String checkExist(String memberId){
        for(String key : HTTP_SESSION_MAP.keySet()){
           if(key.endsWith(memberId)){
               return key;
           }
        }
        return null;
    }

    /**
     * 返回在线用户数
     * @return
     */
    public static int getOnlineCount(){
        return HTTP_SESSION_MAP.size();
    }
}
