package com.isfa.clientadminpanel.leave.model;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isfa.clientadminpanel.leave.entities.Color;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ColorRequest extends BaseRequest{

	public static final Logger logger = LoggerFactory.getLogger(ColorRequest.class);
	
	private Long id;
    private String type;
    private Boolean active;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime modifiedDate;
    private String modifiedBy;
    
    
	public Color convertInto() {
		logger.info("ColorRequest method converting ColorRequest obj to Color obj executing");
		Color color = new Color();
		
		color.setId(this.getId());
		color.setType(this.getType());
		color.setActive(this.getActive());
		color.setCreatedDate(this.getCreatedDate());
		color.setCreatedBy(this.getCreatedBy());
		color.setModifiedDate(this.getModifiedDate());
		color.setModifiedBy(this.getModifiedBy());
		logger.info("ColorRequest method converting ColorRequest obj to Color obj completed");
		return color;
	}

}
