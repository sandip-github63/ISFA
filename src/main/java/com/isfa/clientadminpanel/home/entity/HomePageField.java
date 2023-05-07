package com.isfa.clientadminpanel.home.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "home_page_fields")
@Data
public class HomePageField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fieldName;

    @Lob
    private byte[] iconImage;

    private String redirectUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public byte[] getIconImage() {
		return iconImage;
	}

	public void setIconImage(byte[] iconImage) {
		this.iconImage = iconImage;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public HomePageField(Long id, String fieldName, byte[] iconImage, String redirectUrl) {
		super();
		this.id = id;
		this.fieldName = fieldName;
		this.iconImage = iconImage;
		this.redirectUrl = redirectUrl;
	}

	public HomePageField() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
