package com.isfa.clientadminpanel.promoter.entities;

import java.time.LocalDateTime;
import javax.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "product")
public class Product {

	public static final Logger logger = LoggerFactory.getLogger(Product.class);
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_code")
    private String productCode;

    @Column(name="price")
    private Double price;

    @Column(name = "created_date",updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "created_by",updatable = false)
    private String createdBy;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "modified_by")
    private String modifiedBy;

	
    //no-args constructor
    public Product() {
		super();
		logger.info("Product no-args constructor executed");
	}


    //parameterized cons
	public Product(Long productId, Long categoryId, String productName, String productCode, Double price, LocalDateTime createdDate,
			String createdBy, LocalDateTime modifiedDate, String modifiedBy) {
		super();
		logger.info("Product parameterized constructor executing");
		this.productId = productId;
		this.categoryId = categoryId;
		this.productName = productName;
		this.productCode = productCode;
		this.price = price;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		logger.info("Product parameterized constructor completed");
	}

    
}
