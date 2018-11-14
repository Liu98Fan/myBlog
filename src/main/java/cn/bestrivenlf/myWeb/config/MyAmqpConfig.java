package cn.bestrivenlf.myWeb.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: liufan
 * @Date: 2018/10/18 20:21
 * @Description:
 */
@Configuration
public class MyAmqpConfig {
    //自定义json序列化消息
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
