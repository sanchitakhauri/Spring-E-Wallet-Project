package com.geeksforgeeks.emailServiceMajorProject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfiguration {
    @Bean
    JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("sanchitakhauri1994@gmail.com");
        javaMailSender.setPassword("sanchit@1994");
        Properties props= javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol","smtp");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put ("mail.debug","true");
        return javaMailSender;
    }
    @Bean
    SimpleMailMessage simpleMailMessage(){
        return new SimpleMailMessage();
    }
}
