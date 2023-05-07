package com.isfa.promoter.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "stock_assignment")
public class StockAssignment {

	public static final Logger logger = LoggerFactory.getLogger(StockAssignment.class);
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transId;
    
    @Column(name = "store_id")
    private Long storeId;
    
    @Column(name = "product_id")
    private Long productId;
    
    @Column(name = "trans_unit")
    private Long transUnit;
    
    @Column(name = "trans_type")
    private String transType;
    
    @Column(name = "transaction_type")
    private String transactionType;
    
    @Column(name="price")
    private Double price;
    
    @Column(name="total_price")
    private Double totalPrice;
    
    @Column(name = "created_date",updatable = false)
    private LocalDateTime createdDate;
    
    @Column(name = "created_by",updatable = false)
    private String createdBy;
    
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;
    
    @Column(name = "modified_by")
    private String modifiedBy;

    
    //no-args constructor
	public StockAssignment() {
		super();
		logger.info("StockAssignment no-args constructor executed");
	}

	
	//parameterized constructor
	public StockAssignment(Long transId, Long storeId, Long productId, Long transUnit, String transType,
			String transactionType, Double price, Double totalPrice, LocalDateTime createdDate, String createdBy,
			LocalDateTime modifiedDate, String modifiedBy) {
		super();
		logger.info("StockAssignment parameterized constructor executing");
		this.transId = transId;
		this.storeId = storeId;
		this.productId = productId;
		this.transUnit = transUnit;
		this.transType = transType;
		this.transactionType = transactionType;
		this.price = price;
		this.totalPrice = totalPrice;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		logger.info("StockAssignment parameterized constructor completed");
	}

    
    
}
