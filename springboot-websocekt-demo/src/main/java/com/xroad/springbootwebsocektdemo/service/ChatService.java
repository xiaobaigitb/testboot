package com.xroad.springbootwebsocektdemo.service;

import com.alibaba.fastjson.JSONObject;
import com.xroad.springbootwebsocektdemo.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

/**
 * 该实例的点对点推送消息，群发的处理代码都在这个业务处理类里面了，
 * 简单点就是 convertAndSend 和 convertAndSendToUser的使用， 一个是群发，一个是单发（单发会在主题前拼接/user）
 *
 **/
@Service
public class ChatService {

    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;


    public Boolean sendMsg(String msg) {
        try {
            JSONObject msgJson = JSONObject.parseObject(msg);
            if (msgJson.getString("to").equals("all") && msgJson.getString("type").equals(ChatMessage.MessageType.CHAT.toString())){
                simpMessageSendingOperations.convertAndSend("/topic/public", msgJson);

            }else if (msgJson.getString("to").equals("all") && msgJson.getString("type").equals(ChatMessage.MessageType.JOIN.toString())) {
                simpMessageSendingOperations.convertAndSend("/topic/public", msgJson);

            }else if(msgJson.getString("to").equals("all") &&  msgJson.getString("type").equals(ChatMessage.MessageType.LEAVE.toString())) {
                simpMessageSendingOperations.convertAndSend("/topic/public", msgJson);

            }else if (!msgJson.getString("to").equals("all") &&  msgJson.getString("type").equals(ChatMessage.MessageType.CHAT.toString())){
                simpMessageSendingOperations.convertAndSendToUser(msgJson.getString("to"),"/topic/msg", msgJson);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }


}
