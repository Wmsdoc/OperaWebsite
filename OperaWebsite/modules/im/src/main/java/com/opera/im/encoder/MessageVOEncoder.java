//package com.opera.im.encoder;
//
//import com.opera.im.domain.MessageVO;
//import jakarta.websocket.EncodeException;
//import jakarta.websocket.Encoder;
//import jakarta.websocket.EndpointConfig;
//
//public class MessageVOEncoder implements Encoder.Text<MessageVO> {
//
//    @Override
//    public String encode(MessageVO message) throws EncodeException {
//        // 将 MessageVO 对象转换为文本格式
//        // 这里可以根据需要进行自定义编码逻辑
//        return message.toString();
//    }
//
//    @Override
//    public void init(EndpointConfig config) {
//        // 初始化编码器
//    }
//
//    @Override
//    public void destroy() {
//        // 销毁编码器
//    }
//}
//
