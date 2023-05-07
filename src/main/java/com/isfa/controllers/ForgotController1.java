package com.isfa.controllers;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isfa.Exception.BadRequestException;
import com.isfa.Exception.ResourceNotFoundException;
import com.isfa.mail.service.MailService;
import com.isfa.models.BaseResponseAuth;
import com.isfa.models.User;
import com.isfa.payload.request.ForgotPasswordRequest;
import com.isfa.payload.request.ResetPasswordRequest;
import com.isfa.payload.request.VerifyOtpRequest;
import com.isfa.payload.response.ForgotPasswordResponse;
import com.isfa.repository.UserRepository;

@RestController
@RequestMapping("/api/auth/v1")
public class ForgotController1 {

//    @Autowired
//    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailService mailService;
    
   

    private static final String OTP_CHARS = "0123456789";
    private static final int OTP_LENGTH = 4;
    


    @PostMapping("/forgot-password")
    public ResponseEntity<BaseResponseAuth<ForgotPasswordResponse>> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        System.out.println("user  "+user);
        System.out.println("request  "+request.getEmail());
        
        if (user == null) {
            throw new ResourceNotFoundException("User not found with email: " + request.getEmail());
        }

//        String otp = generateOTP();
//        user.setResetPasswordOTP(otp);
//        userRepository.save(user);
//        String otp = generateOTP();
//        user.setResetPasswordOTP(otp);
//        userRepository.save(user);
        
        
        mailService.mailSend(request.getEmail());
        
        
        ForgotPasswordResponse forgotPasswordResponse = new ForgotPasswordResponse("OTP generated successfully");
        BaseResponseAuth<ForgotPasswordResponse> baseResponse = new BaseResponseAuth<>();
        baseResponse.setMessage("OTP sent successfully. Please check your email.");
        baseResponse.setStatus(HttpStatus.OK.toString());
        baseResponse.setData(forgotPasswordResponse);

        return ResponseEntity.ok(baseResponse);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<BaseResponseAuth<ForgotPasswordResponse>> verifyOTP(@RequestBody VerifyOtpRequest request) {
        User user = userRepository.findByEmail(request.getEmailId());
        if (user == null) {
            throw new ResourceNotFoundException("User not found with email: " + request.getEmailId());
        }

        if (!user.getResetPasswordOTP().equals(request.getOtp())) {
            throw new BadRequestException("Invalid OTP");
        }

        ForgotPasswordResponse forgotPasswordResponse = new ForgotPasswordResponse("OTP verified successfully");
        BaseResponseAuth<ForgotPasswordResponse> baseResponse = new BaseResponseAuth<>();
        baseResponse.setMessage("OTP verified successfully");
        baseResponse.setStatus(HttpStatus.OK.toString());
        baseResponse.setData(forgotPasswordResponse);

        return ResponseEntity.ok(baseResponse);
    }


    @PostMapping("/reset-password")
    public ResponseEntity<BaseResponseAuth<ForgotPasswordResponse>> resetPassword(@RequestBody ResetPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new ResourceNotFoundException("User not found with email: " + request.getEmail());
        }

        if (!user.getResetPasswordOTP().equals(request.getOtp())) {
            throw new BadRequestException("Invalid OTP");
        }

        user.setPassword((request.getPassword()));
        user.setResetPasswordOTP(null);
        userRepository.save(user);

        ForgotPasswordResponse forgotPasswordResponse = new ForgotPasswordResponse("Password reset successfully");
        BaseResponseAuth<ForgotPasswordResponse> baseResponse = new BaseResponseAuth<>();
        baseResponse.setMessage("Password reset successfully");
        baseResponse.setStatus(HttpStatus.OK.toString());
        baseResponse.setData(forgotPasswordResponse);

        return ResponseEntity.ok(baseResponse);
    }


    private String generateOTP() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            int index = (int) (OTP_CHARS.length() * Math.random());
            otp.append(OTP_CHARS.charAt(index));
        }
        return otp.toString();
    }
    



//    private void sendEmail(String to, String subject, String text) {
//        System.out.println("send email function started");
//    	SimpleMailMessage message = new SimpleMailMessage();
//        
//        message.setFrom("isfa@denave.com");
//
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        System.out.println("send email function started step 2");
//        mailSender.send(message);
//        System.out.println("send email function ended");
//    }
}





