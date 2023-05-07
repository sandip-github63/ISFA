package com.isfa.clientadminpanel.promoter.model;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isfa.clientadminpanel.promoter.entities.Category;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CategoryRequest extends BaseRequest {

	public static final Logger logger = LoggerFactory.getLogger(CategoryRequest.class);
	
    private Long categoryId;

    private String categoryName;

    private LocalDateTime createdDate;

    private String createdBy;

    private LocalDateTime modifiedDate;

    private String modifiedBy;
	
	
    public Category convertInto(CategoryRequest req) {
    	logger.info("CategoryRequest method converting CategoryRequest obj to Category obj executing");
    	Category category = new Category();
    	
    	category.setCategoryId(req.getCategoryId());
    	category.setCategoryName(req.getCategoryName());
    	category.setCreatedDate(req.getCreatedDate());
    	category.setCreatedBy(req.getCreatedBy());
    	category.setModifiedBy(req.getModifiedBy());
    	category.setModifiedDate(req.getModifiedDate());
    	logger.info("CategoryRequest method converting CategoryRequest obj to Category obj completed");
    	return category;
    }
    
}
