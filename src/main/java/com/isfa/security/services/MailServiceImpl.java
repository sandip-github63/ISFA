//package com.isfa.security.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MailServiceImpl implements MailService {
//
//    private JavaMailSender mailSender;
//
//    @Autowired
//    public MailServiceImpl(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }
//
//    @Override
//    public void sendForgotPasswordEmail(String email, String pin) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject("Forgot Password");
//        message.setText("Your PIN is " + pin);
//        mailSender.send(message);
//    }
//}
