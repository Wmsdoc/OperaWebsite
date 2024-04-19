package com.opera.im.controller;

import com.opera.im.context.WebSocketContext;
import com.opera.im.enums.MsgTypeEnum;
import com.opera.im.message.ChatMsgMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * top.bulk.lock.zk.controller
 *
 * @author 散装java
 */
@Controller
@RequestMapping("/socket")
public class WebSocketTestController {
    /**
     * 测试发送消息
     *
     * @param str 消息
     */
    @GetMapping("/send/{str}")
    public void send(@PathVariable("str") String str) {
        ChatMsgMessage chatMsgMessage = new ChatMsgMessage().setMsg(str);
        WebSocketContext.broadcast(MsgTypeEnum.CHAT_MSG.getCode(), chatMsgMessage, null);
    }

}
