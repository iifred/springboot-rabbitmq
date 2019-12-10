package com.fred.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/***
 * 自动配置
 *  1、RabbitAutoConfiguration
 *  2、又自动配置了连接工厂 rabbitConnectionFactory
 *  3、amqpadmin,rabbitmq系统管理功能组件 (创建申明交换器，创建申明队列)
 */
@SpringBootApplication
@EnableRabbit
public class SpringbootRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitmqApplication.class, args);
    }

}
