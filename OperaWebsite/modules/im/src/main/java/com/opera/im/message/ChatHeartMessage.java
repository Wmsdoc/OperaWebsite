package com.opera.im.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.opera.activity.domain.Playgoer;
import com.opera.im.enums.MsgTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 发送给所有人的群聊消息的 Message
 *
 * @author 散装java
 */
@Data
@Accessors(chain = true)
public class ChatHeartMessage implements Message {

    public static final String TYPE = MsgTypeEnum.CHAT_MSG.getCode();

    /**
     * 内容
     */
    private String msg;

}