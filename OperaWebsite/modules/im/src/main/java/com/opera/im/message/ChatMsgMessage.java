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
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class ChatMsgMessage extends Playgoer implements Message {

    public static final String TYPE = MsgTypeEnum.CHAT_MSG.getCode();

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String msg;
    /**
     * 发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sendTime;
}
