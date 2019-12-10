package com.fred.amqp;

import com.fred.amqp.bean.Book;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootRabbitmqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;
    /**
     * 1、单播（点对点）
     */
    @Test
    void contextLoads() {
//        Message需要自己构造一个;定义消息体内容喝消息头
//        rabbitTemplate.send(exchange, routeKey, message);
//          object默认当成消息体只需要传入要发送的对象，自动序列化发送给rabbitmq
//          rabbitTemplate.convertAndSend(exchange,routeKey,object);

        Map<String, Object> map = new HashMap<>();
        map.put("msg","这是第一个消息");
        map.put("data", Arrays.asList("hello", 123, true));
        //对象被默认序列化以后发送出去
//        rabbitTemplate.convertAndSend("exchange.direct","gulixueyuan.news",map);
        rabbitTemplate.convertAndSend("exchange.direct","gulixueyuan.news",new Book("三国演义","刘智飞"));
    }
    //接收数据，如何将数据自动的转为json发送出去
    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("gulixueyuan.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    //广播
    @Test
    public void sendMsg(){
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("小狗钱钱","骑上东坡"));
    }

    @Test
    public void createExchange(){
        //创建一个交换器
//        amqpAdmin.declareExchange(new DirectExchange("amqp.exchange"));
        //创建一个队列
//        amqpAdmin.declareQueue(new Queue("amqp.exchangequeue"));
        //创建绑定规则
        amqpAdmin.declareBinding(new Binding("amqp.exchangequeue",Binding.DestinationType.QUEUE,"amqp.exchange","amqp.fred",null));
    }
}
