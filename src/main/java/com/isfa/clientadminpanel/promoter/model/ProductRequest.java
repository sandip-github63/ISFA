package com.isfa.clientadminpanel.promoter.model;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isfa.clientadminpanel.promoter.entities.Product;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductRequest extends BaseRequest{

	public static final Logger logger = LoggerFactory.getLogger(ProductRequest.class);
	
	
	private Long productId;

    private Long categoryId;

    private String productName;

    private String productCode;

    private double price;

    private LocalDateTime createdDate;

    private String createdBy;

    private LocalDateTime modifiedDate;

    private String modifiedBy;


    public Product convertInto(ProductRequest request) {
    	logger.info("ProductRequest method converting ProductRequest obj to Product obj executing");
    	
    	Product product = new Product();
    	
    	product.setProductId(this.productId);
    	product.setCategoryId(this.categoryId);
    	product.setProductName(this.productName);
    	product.setProductCode(this.productCode);
    	product.setPrice(this.price);
    	product.setCreatedDate(this.createdDate);
    	product.setCreatedBy(this.createdBy);
    	product.setModifiedDate(this.modifiedDate);
    	product.setModifiedBy(this.modifiedBy);
    	logger.info("ProductRequest method converting ProductRequest obj to Product obj completed");
    	return product;
    	
    }
    
}
