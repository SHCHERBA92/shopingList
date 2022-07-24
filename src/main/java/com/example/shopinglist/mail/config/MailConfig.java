package com.example.shopinglist.mail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(MailConstant.HOST_NAME);
        mailSender.setPort(MailConstant.PORT);
        mailSender.setUsername(MailConstant.USER_NAME);
        mailSender.setPassword(MailConstant.PASSWORD);
        mailSender.setProtocol(MailConstant.PROTOCOL);

//        mailSender.setHost("smtp.yandex.ru");
//        mailSender.setPort(465);
//        mailSender.setUsername("kirillshcherba92@yandex.ru");
//        mailSender.setPassword("zxlzinysirgusrqy");
//        mailSender.setProtocol("smtp");

        var props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.ssl.enable", "true");

        return mailSender;
    }
}
