package com.example.shopinglist.services;

import com.example.shopinglist.DTO.mail.Mail_DTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActiveProducerService {

    private final ObjectMapper objectMapper;
    private final JmsTemplate jmsTemplate;

    public ActiveProducerService(ObjectMapper objectMapper, JmsTemplate jmsTemplate) {
        this.objectMapper = objectMapper;
        this.jmsTemplate = jmsTemplate;
    }


    public void sendEmail(Mail_DTO mail_dto){
        try {
            jmsTemplate.convertAndSend("email_to_sender", objectMapper.writeValueAsString(mail_dto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
