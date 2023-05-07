/** package com.isfa.security.services;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
public class MailConfig {
	
	
	
	 private static final String USERNAME = "isfa";
	    private static final String PASSWORD = "IndiaTV@2022";
	    private static final String POP_HOST = "pop.denave.com";
	    private static final String SMTP_HOST = "smtp.denave.com";
	    private static final int POP_PORT = 110;
	    private static final int SMTP_PORT = 587;
	    
	    
    @Bean
    public JavaMailSender javaMailSender() {
        
    	System.out.println("mail config called");
    	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        
        
        mailSender.setHost(SMTP_HOST);
        mailSender.setPort(SMTP_PORT);

        mailSender.setUsername(USERNAME);
        mailSender.setPassword(PASSWORD);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.trust", SMTP_HOST);
        props.put("mail.smtp.timeout", "50000");

        System.out.println("mail config done");
        return mailSender;
        
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//        mailSender.setUsername("");
//        mailSender.setPassword("");
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//
//        return mailSender;
    }
}

*/
