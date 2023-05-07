package com.isfa.mail.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration("em")
public class EmailUtils 
{

	@Value("${mail.username}")
	private String mail_username;
	
	@Value("${mail.password}")
	private String mail_password;
	
	@Value("${mail.smtp.port}")
	private String smtp_port;
	
	@Value("${mail.smtp.host}")
	private String smtp_host;
	
	@Value("${mail.auth}")
	private String mail_auth;
	
	@Value("${mail.starttls.enable}")
	private String starttls_enable;
	
	
	@Value("${mail.from}")
	private String mail_from;


	public String getMail_username() {
		return mail_username;
	}


	public void setMail_username(String mail_username) {
		this.mail_username = mail_username;
	}


	public String getMail_password() {
		return mail_password;
	}


	public void setMail_password(String mail_password) {
		this.mail_password = mail_password;
	}


	public String getSmtp_port() {
		return smtp_port;
	}


	public void setSmtp_port(String smtp_port) {
		this.smtp_port = smtp_port;
	}


	public String getSmtp_host() {
		return smtp_host;
	}


	public void setSmtp_host(String smtp_host) {
		this.smtp_host = smtp_host;
	}


	public String getMail_auth() {
		return mail_auth;
	}


	public void setMail_auth(String mail_auth) {
		this.mail_auth = mail_auth;
	}


	public String isStarttls_enable() {
		return starttls_enable;
	}


	public void setStarttls_enable(String starttls_enable) {
		this.starttls_enable = starttls_enable;
	}


	public String getMail_from() {
		return mail_from;
	}


	public void setMail_from(String mail_from) {
		this.mail_from = mail_from;
	}


	public EmailUtils(String mail_username, String mail_password, String smtp_port, String smtp_host, String mail_auth,
			String starttls_enable, String mail_from) {
		super();
		this.mail_username = mail_username;
		this.mail_password = mail_password;
		this.smtp_port = smtp_port;
		this.smtp_host = smtp_host;
		this.mail_auth = mail_auth;
		this.starttls_enable = starttls_enable;
		this.mail_from = mail_from;
	}


	public EmailUtils() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
