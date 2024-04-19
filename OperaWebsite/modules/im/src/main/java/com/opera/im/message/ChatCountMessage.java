package com.opera.im.message;

import com.opera.im.enums.MsgTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 聊天室人数
 *
 * @author 散装java
 */
@Data
@Accessors(chain = true)
public class ChatCountMessage implements Message {

    public static final String TYPE = MsgTypeEnum.CHAT_COUNT.getCode();

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private Integer count;

}
