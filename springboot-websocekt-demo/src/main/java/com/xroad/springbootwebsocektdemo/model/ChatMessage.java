package com.xroad.springbootwebsocektdemo.model;

/**
 *
 *接收消息使用到的实体类，处理消息的业务类
 */
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;
    private String to;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "type=" + type +
                ", content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", to='" + to + '\'' +
                '}';
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}