package com.opera.im.context;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.opera.activity.domain.Playgoer;
import com.opera.api.activity.RemoteActivityService;
import com.opera.api.system.RemoteSystemService;
import com.opera.im.enums.MsgTypeEnum;
import com.opera.im.message.ChatCountMessage;
import com.opera.im.message.ChatMsgMessage;
import com.opera.im.message.Message;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.websocket.RemoteEndpoint;
import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.opera.im.enums.MsgTypeEnum.CHAT_MSG;

/**
 * WebSocket 会话上下文工具
 */
@Slf4j
@Service
public class WebSocketContext {

    /**
     * Session 与用户的映射
     */
    private static final Map<Session, String> SESSION_USER_MAP = new ConcurrentHashMap<>();
    /**
     * 用户与 Session 的映射
     */
    private static final Map<String, Session> USER_SESSION_MAP = new ConcurrentHashMap<>();

    // 注入远程服务
    @Resource
    private RemoteActivityService remoteActivityService;
    @Resource
    private RemoteSystemService remoteSystemService;

    // 新建一个静态的变量
    private static RemoteActivityService staticRemoteActivityService;
    private static RemoteSystemService staticRemoteSystemService;

    // 初始化的时候，将@Resource变量赋值给静态变量
    @PostConstruct
    public void init() {
        staticRemoteActivityService = remoteActivityService;
        staticRemoteSystemService = remoteSystemService;
    }

    /**
     * 添加 Session 在这个方法中，会绑定用户和 Session 之间的映射
     *
     * @param session Session
     * @param user    用户
     */
    public static void add(Session session, String user) {
        // 更新 USER_SESSION_MAP , 这里的 user 正常来讲应该是具体的用户(id)，而不是单纯的 session.getId()
        USER_SESSION_MAP.put(user, session);
        // 更新 SESSION_USER_MAP
        SESSION_USER_MAP.put(session, user);
    }

    /**
     * 移除 Session
     *
     * @param session Session
     */
    public static void remove(Session session) {
        // 从 SESSION_USER_MAP 中移除
        String user = SESSION_USER_MAP.remove(session);
        // 从 USER_SESSION_MAP 中移除
        if (user != null && !user.isEmpty()) {
            USER_SESSION_MAP.remove(user);
        }
    }


    /**
     * 广播发送消息给所有在线用户
     *
     * @param type    消息类型
     * @param message 消息体
     * @param <T>     消息类型
     * @param me      当前消息的发送者，不会将消息发送给自己
     */
    public static <T extends Message> void broadcast(String type, T message, Session me) {
        // 创建消息
        String messageText = buildTextMessage(type, message, me);
        // 遍历 SESSION_USER_MAP ，进行逐个发送
        for (Session session : SESSION_USER_MAP.keySet()) {
            if (!session.equals(me)) {
                sendTextMessage(session, messageText);
            }
        }
    }

    /**
     * 发送消息给单个用户的 Session
     *
     * @param session Session
     * @param type    消息类型
     * @param message 消息体
     * @param <T>     消息类型
     */
    public static <T extends Message> void send(Session session, String type, T message) {
        // 创建消息
        String messageText = buildTextMessage(type, message, session);
        // 遍历给单个 Session ，进行逐个发送
        sendTextMessage(session, messageText);
    }

    /**
     * 发送消息给指定用户
     *
     * @param user    指定用户
     * @param type    消息类型
     * @param message 消息体
     * @param <T>     消息类型
     * @return 发送是否成功
     */
    public static <T extends Message> boolean send(String user, String type, T message) {
        // 获得用户对应的 Session
        Session session = USER_SESSION_MAP.get(user);
        if (session == null) {
            log.error("==> user({}) 不存在对应的 session", user);
            return false;
        }
        // 发送消息
        send(session, type, message);
        return true;
    }

    /**
     * 构建完整的消息
     *
     * @param type    消息类型
     * @param message 消息体
     * @param <T>     消息类型
     * @return 消息
     */
    private static <T extends Message> String buildTextMessage(String type, T message, Session me) {
        if (me != null) {
            //根据session获取登录用户ID
            String user = SESSION_USER_MAP.get(me);
            Playgoer playgoer = new Playgoer();

            //远程调用获取用户信息
            Long playgoerId = staticRemoteSystemService.getPlaygoerIdByAccountId(Long.valueOf(user)).getData();
            playgoer = staticRemoteActivityService.remoteGetById(playgoerId).getData();

            //根据type判断消息类型
            if (type.equals(CHAT_MSG.getCode())) {
                //说明是聊天消息
                ChatMsgMessage messageVO = (ChatMsgMessage) message;
                messageVO.setPlaygoerId(playgoer.getPlaygoerId());
                messageVO.setPlaygoerName(playgoer.getPlaygoerName());
                messageVO.setPlaygoerAvatar(playgoer.getPlaygoerAvatar());

                messageVO.setSendTime(new Date());
            } else if (type.equals(MsgTypeEnum.CHAT_COUNT.getCode())) {
                //说明是在线人数消息
                ChatCountMessage messageVO = (ChatCountMessage) message;
                messageVO.setCount(Integer.valueOf(message.toString()));
            }
        }
        JSONObject messageObject = new JSONObject();
        messageObject.put("type", type);
        messageObject.put("body", message);
        return messageObject.toString();
    }

    /**
     * 真正发送消息
     *
     * @param session     Session
     * @param messageText 消息
     */
    private static void sendTextMessage(Session session, String messageText) {
        if (session == null) {
            log.error("===> session 为 null");
            return;
        }
        RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (basic == null) {
            log.error("===> session.basic 为 null");
            return;
        }
        try {
            basic.sendText(messageText);
//            basic.sendText(messageVO.toString());
//            basic.sendObject(messageVO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 在线人数通知
     */
    public static void countNotice() {
        Integer count = USER_SESSION_MAP.size();
        ChatCountMessage message = new ChatCountMessage();
        message.setCount(count);
        broadcast(MsgTypeEnum.CHAT_COUNT.getCode(), message, null);
    }
}
