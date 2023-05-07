package com.isfa.clientadminpanel.leave.model;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isfa.clientadminpanel.leave.entities.Icon;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class IconRequest extends BaseRequest {
    
	public static final Logger logger = LoggerFactory.getLogger(IconRequest.class);
	
	private Long iconId;
	private String iconType;
	private boolean active;
	private LocalDateTime createdDate;
	private String createdBy;
	private LocalDateTime modifiedDate;
	private String modifiedBy;

    
	
	public Icon convertInto() {
		logger.info("IconRequest method converting IconRequest obj to Color obj executing");
		Icon icon = new Icon();
		icon.setIconId(this.getIconId());
		icon.setIconType(this.getIconType());
		icon.setActive(this.isActive());
		icon.setCreatedDate(this.getCreatedDate());
		icon.setCreatedBy(this.getCreatedBy());
		icon.setModifiedDate(this.getModifiedDate());
		icon.setModifiedBy(this.getModifiedBy());
		logger.info("IconRequest method converting IconRequest obj to Color obj completed");
		return icon;
	}
}
