package com.example.shopinglist.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Basic;

@Configuration
public class ObjectConfig {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
