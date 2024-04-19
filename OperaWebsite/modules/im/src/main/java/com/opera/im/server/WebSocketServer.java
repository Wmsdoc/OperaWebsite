package com.opera.im.server;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opera.im.context.WebSocketContext;
import com.opera.im.enums.MsgTypeEnum;
import com.opera.im.message.ChatHeartMessage;
import com.opera.im.message.ChatMsgMessage;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * 配置接入点
 * ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@Component
@ServerEndpoint(value = "/chat/{token}")
@Slf4j
public class WebSocketServer {

    private ScheduledExecutorService scheduler;

    private void startHeartbeat(Session session) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        // 每隔一段时间发送心跳消息
        scheduler.scheduleAtFixedRate(() -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg", "pong");
            ChatHeartMessage msg = JSON.parseObject(jsonObject.toJSONString(), ChatHeartMessage.class);
            // 发送心跳消息
            WebSocketContext.send(session, MsgTypeEnum.CHAT_HEART.getCode(), msg);
        }, 0, 10, TimeUnit.SECONDS); // 每10秒发送一次心跳消息
    }

    private void stopHeartbeat() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "token") String token) {
        //获取当前登录的用户ID
//        String accountId = StpUtil.getLoginIdAsString();
        if (token != null && !token.equals("null")) {
            String accountId = StpUtil.getLoginIdByToken(token).toString();
            // 上线，并且通知到其他人
            log.info("===> onOpen--accountId:{}", accountId);
            WebSocketContext.add(session, accountId);
        } else {
            //用户未进行登录操作
            log.info("===> onOpen--session:{}", session.getId());
            WebSocketContext.add(session, session.getId());
        }
        WebSocketContext.countNotice();
        // 启动心跳监测任务
        startHeartbeat(session);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, @PathParam(value = "token") String token) {
        //获取当前登录的用户ID
//        String accountId = StpUtil.getLoginIdAsString();
        log.info("===> onClose:{}", token);
        // 关闭心跳监测任务
        stopHeartbeat();
        // 下线，并且通知到其他人
        WebSocketContext.remove(session);
        WebSocketContext.countNotice();

    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("===> onMessage:{},message:{}", session.getId(), message);
        if (Objects.equals(message.replace("\"", ""), "ping")) {
            //说明发送的心跳数据
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "pong");
            ChatHeartMessage msg = JSON.parseObject(jsonObject.toJSONString(), ChatHeartMessage.class);
            // 发送心跳消息
            WebSocketContext.send(session, MsgTypeEnum.CHAT_HEART.getCode(), msg);
        }
        // 进行消息的转发，同步到其他的客户端上
        ChatMsgMessage msg = JSON.parseObject(message, ChatMsgMessage.class);
        WebSocketContext.broadcast(MsgTypeEnum.CHAT_MSG.getCode(), msg, session);
    }

    /**
     * 监听错误
     *
     * @param session session
     * @param error   错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("SessionId:{},出现异常：{}", session.getId(), error.getMessage());
        error.printStackTrace();
    }

}
