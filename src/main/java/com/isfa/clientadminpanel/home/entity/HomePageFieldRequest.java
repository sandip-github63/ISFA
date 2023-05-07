package com.isfa.clientadminpanel.home.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class HomePageFieldRequest {
    private String fieldName;
    private MultipartFile iconImage;
    private String redirectUrl;
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public MultipartFile getIconImage() {
		return iconImage;
	}
	public void setIconImage(MultipartFile iconImage) {
		this.iconImage = iconImage;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public HomePageFieldRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HomePageFieldRequest(String fieldName, MultipartFile iconImage, String redirectUrl) {
		super();
		this.fieldName = fieldName;
		this.iconImage = iconImage;
		this.redirectUrl = redirectUrl;
	}
    
    
}
