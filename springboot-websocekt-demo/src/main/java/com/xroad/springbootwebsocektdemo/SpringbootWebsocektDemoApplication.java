package com.xroad.springbootwebsocektdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1.使用stomp协议
 *
 * 2.使用rabbitmq作为消息代理
 *
 * 3.使用rabbitmq接收发送消息
 *
 * 4.推送消息的确认，消费消息的确认
 *
 * 5.简单的页面配合收发消息测试
 *
 * 6. 通过连接websocekt时传递个人信息参数，后台接收（拦截获取参数）
 *
 * 7.一对一发收，一对多发收
 */
@SpringBootApplication
public class SpringbootWebsocektDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebsocektDemoApplication.class, args);
    }

}
