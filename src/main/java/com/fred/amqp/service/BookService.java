package com.fred.amqp.service;

import com.fred.amqp.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @RabbitListener(queues = "gulixueyuan.news")
    public void receive(Book book){
        System.out.println("收到消息:"+book.toString());
    }

    @RabbitListener(queues = "atguigu.emps")
    public void receive1(Message message){
        System.out.println("收到消息:"+message);
//        System.out.println("收到消息:"+message.getMessageProperties().getHeaders());
    }
}
