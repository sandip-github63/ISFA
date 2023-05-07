package com.isfa.mail.service;

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
import org.springframework.stereotype.Service;

import com.isfa.mail.model.EmailUtils;
import com.isfa.models.User;
import com.isfa.repository.UserRepository;

@Service
public class MailService 
{
	
	@Autowired
	EmailUtils em;
	
	@Autowired
    private UserRepository userRepository;
    
	
	private static final String OTP_CHARS = "0123456789";
    private static final int OTP_LENGTH = 4;
    

	public ResponseEntity mailSend(String mail_to)
	{
			Properties props = System.getProperties();  
	        props.put("mail.smtp.host",em.getSmtp_host());
	        props.put("mail.smtp.port",Integer.parseInt(em.getSmtp_port()));
			props.put("mail.smtp.auth",em.getMail_auth());
			props.put("mail.smtp.starttls.enable",em.isStarttls_enable());
			
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(em.getMail_username(),em.getMail_password());
				}
			};
			
	      Session session = Session.getDefaultInstance(props,auth);  
		
	      
		 try{  
	           
			 User user = userRepository.findByEmail(mail_to);
	         String otp = generateOTP();
	         user.setResetPasswordOTP(otp);
	         userRepository.save(user);

	         MimeMessage message = new MimeMessage(session);
	         String subject = "Reset your password";
	         String text = "Your OTP to reset password is: " + otp;
	         
	         
	         
	         message.setFrom(new InternetAddress(em.getMail_from()));  
	         message.addRecipient(Message.RecipientType.TO,new InternetAddress(mail_to));  
	         message.setSubject("Reset your password");  
	         message.setText("Your OTP to reset password is: " + otp);  
	  
	         Transport.send(message);  
	         System.out.println("message sent successfully....");
	         
	         return new ResponseEntity("Mail Sent",HttpStatus.OK);
	  
	      }
		  catch (MessagingException mex) 
		  {
			  return new ResponseEntity(mex.getMessage(),HttpStatus.BAD_REQUEST);
					  
	      }
		
		
	}
	private String generateOTP() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            int index = (int) (OTP_CHARS.length() * Math.random());
            otp.append(OTP_CHARS.charAt(index));
        }
        return otp.toString();
    }
    
	
}
