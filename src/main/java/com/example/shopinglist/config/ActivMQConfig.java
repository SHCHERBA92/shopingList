package com.example.shopinglist.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

@Configuration
public class ActivMQConfig {

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("email_to_sender");
    }
}
