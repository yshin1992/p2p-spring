package org.p2p.controller.sys;

import org.p2p.cache.SessionCache;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务器端推送消息
 */
@RestController
@RequestMapping("/sse")
public class SSEController {


    /**
     * 推送在线用户数
     * @return
     */
    @RequestMapping(value="/pushcount",produces={"text/event-stream;charset=utf-8"})
    public String pushOnlineCount(){

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data:" + SessionCache.getOnlineCount() + "\n\n";
    }

}
