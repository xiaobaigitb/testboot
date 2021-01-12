package com.xroad.springbootwebsocektdemo.config.rabbitmq;

import com.xroad.springbootwebsocektdemo.service.ChatService;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1.连接rabbitmq代码
 * 2.简单使用一下主题交换机
 * （其实可以配置更多的交换机，对于使用rabbitmq方面，完全没接触过的小伙伴，不妨先看看这篇文章，《Springboot 整合RabbitMq ，用心看完这一篇就够了》 链接： https://blog.csdn.net/qq_35387940/article/details/100514134）
 *
 * 3.配置生产者推送消息触发的回调函数
 * 4.消费者监听器，采取开启手动确认
 */

@Configuration
public class MyRabbitConfig {

    @Autowired
    ChatService chatService;


    //绑定键
    public final static String msgTopicKey = "topic.public";
    //队列
    public final static String msgTopicQueue = "topicQueue";

    @Bean
    public Queue topicQueue() {
        return new Queue(MyRabbitConfig.msgTopicQueue,true);
    }


    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicWebSocketExchange",true,false);
    }


    //将firstQueue和topicExchange绑定,而且绑定的键值为topic.man
    //这样只要是消息携带的路由键是topic.man,才会分发到该队列
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(topicQueue()).to(exchange()).with(msgTopicKey);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost", 5672);
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        connectionFactory.setVirtualHost("Xroad");
        connectionFactory.setPublisherConfirms(true); // 发送消息回调,必须要设置
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }


    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("ConfirmCallback:     "+"相关数据："+correlationData);
                System.out.println("ConfirmCallback:     "+"确认情况："+ack);
                System.out.println("ConfirmCallback:     "+"原因："+cause);
            }
        });

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("ReturnCallback:     "+"消息："+message);
                System.out.println("ReturnCallback:     "+"回应码："+replyCode);
                System.out.println("ReturnCallback:     "+"回应信息："+replyText);
                System.out.println("ReturnCallback:     "+"交换机："+exchange);
                System.out.println("ReturnCallback:     "+"路由键："+routingKey);
            }


        });

        return rabbitTemplate;
    }




    /**
     * 接受消息的监听，这个监听会接受消息队列topicQueue的消息
     * 针对消费者配置
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer messageContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
        container.setQueues(topicQueue());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener(new ChannelAwareMessageListener() {
            public void onMessage(Message message, com.rabbitmq.client.Channel channel) throws Exception {
                byte[] body = message.getBody();
                String msg = new String(body);
                System.out.println("rabbitmq收到消息 : " +msg);
                Boolean sendToWebsocket = chatService.sendMsg(msg);

                if (sendToWebsocket){
                    System.out.println("消息处理成功！ 已经推送到websocket！");
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), true); //确认消息成功消费

                }
            }

        });
        return container;
    }

}
