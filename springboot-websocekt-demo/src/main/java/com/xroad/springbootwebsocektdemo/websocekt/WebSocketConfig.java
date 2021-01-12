package com.xroad.springbootwebsocektdemo.websocekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/**
 * WebSocketConfig 代码简析及注意点 ：
 * 1.@EnableWebSocketMessageBroker
 *
 * 这个注解作用是为了 是程序能够识别出 连接了 暴露的stomp节点 发送过来的 请求。 配合 @MessageMapping 是用 。（后面有介绍）
 *
 * 2.registry.addEndpoint("/ws")
 *
 * 这就是暴露节点，就是可以让客户端连接到websocket的节点。 如果需要暴露多个，就一样使用registry.addEndpoint("/XXX")即可。
 *
 * 3.configureMessageBroker
 *
 * 配置消息代理，前言说到我们该篇会使用rabbitmq来作为消息代理，所以咱们配置的代码就是使用的rabbitmq。
 *
 * 如果不想整合rabbitmq的话，单纯开启简单的消息代理，两三行代码即可。
 * registry.enableSimpleBroker("/topic","/queue");
 * registry.setApplicationDestinationPrefixes("/app");
 * registry.setUserDestinationPrefix("/user");
 * registry.enableSimpleBroker("/topic","/queue") ：
 *
 * 意思是在暴露的节点上，消息代理即将会处理 前缀为 /topic 和 /queue 的请求消息。 （可以理解为，服务端给客户端推送消息使用）
 *
 * registry.setApplicationDestinationPrefixes("/app") ：
 *
 * 意思是客户端程序访问服务器，需带有/app 前缀，那么这些带有/app的消息就会匹配到@MessageMapping注解的方法上。
 *
 * （可以理解为客户端向服务端推送消息使用）
 * registry.setUserDestinationPrefix("/user") ：
 *
 * 这个就比较好玩，就是服务端指定给用户一对一推送消息，使用 sendToUser 方法时，会帮我们默认拼接上 /user，所以客户端也需要订阅相关的/user前缀的主题才能正常接收。（后面代码还会介绍到）
 *
 *
 *
 * 而我们该篇是使用rabbitmq作为消息代理，所以咱们的代码是：
 * 这些代码就没啥还解释了，上面都说了核心的几个代码意义了，其余就是连接rabbbitmq的一些相关信息，账号密码，虚拟host等。
 *          registry.setUserDestinationPrefix("/user"); //这是给sendToUser使用,前端订阅需要加上/user
 *          registry.setApplicationDestinationPrefixes("/app"); //这是给客户端推送消息到服务器使用 ，推送的接口加上/app
 *         // "STOMP broker relay"处理所有消息将消息发送到外部的消息代理
 *         registry.enableStompBrokerRelay("/exchange","/topic","/queue","/amq/queue")
 *                 .setVirtualHost("JCChost")
 *                 .setRelayHost("localhost")
 *                 .setClientLogin("root")
 *                 .setClientPasscode("root")
 *                 .setSystemLogin("root")
 *                 .setSystemPasscode("root")
 *                 .setSystemHeartbeatSendInterval(5000)
 *                 .setSystemHeartbeatReceiveInterval(4000);
 *
 *再来看 configureClientInboundChannel ，
 *
 * 这个就比较关键，使用客户端绑定配置，咱们绑定了一个拦截器 getHeaderParamInterceptor ，从命名看就知道，就是为了拦截一些参数。
 *
 * 没错，为了识别客户端连接到咱们的websocket上，到底是谁？ 我们该篇采取的就是，让客户端连接时携带自己的个人唯一识别信息过来，在建立连接时，取出该参数，为其实现用户信息认证设置。
 *
 *
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Autowired
    GetHeaderParamInterceptor getHeaderParamInterceptor;
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*")
                .withSockJS();
        //暴露多个节点,就一样直接addEndpoint 就可以
//        registry.addEndpoint("/alone") .setAllowedOrigins("*")
//               .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        //如果不使用rabbitmq作为消息代理,那么只需要暴露简单节点即可
        // 默认的Simple Broker
        //   registry.enableSimpleBroker("/topic","/user");
        //  registry.setApplicationDestinationPrefixes("/app");


        // 使用RabbitMQ做为消息代理，替换默认的Simple Broker
        //定义了服务端接收地址的前缀，也即客户端给服务端发消息的地址前缀,@SendTo(XXX) 也可以重定向

        registry.setUserDestinationPrefix("/user"); //这是给sendToUser使用,前端订阅需要加上/user

        registry.setApplicationDestinationPrefixes("/app"); //这是给客户端推送消息到服务器使用 ，推送的接口加上/app
        // "STOMP broker relay"处理所有消息将消息发送到外部的消息代理
        registry.enableStompBrokerRelay("/exchange","/topic","/queue","/amq/queue")
                .setVirtualHost("Xroad") //对应自己rabbitmq里的虚拟host
                .setRelayHost("localhost")
                .setClientLogin("root")
                .setClientPasscode("root")
                .setSystemLogin("root")
                .setSystemPasscode("root")
                .setSystemHeartbeatSendInterval(5000)
                .setSystemHeartbeatReceiveInterval(4000);

    }
    /**
     * 采用自定义拦截器，获取connect时候传递的参数
     *
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(getHeaderParamInterceptor);
    }
}