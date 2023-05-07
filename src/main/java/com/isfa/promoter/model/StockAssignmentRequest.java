package com.isfa.promoter.model;

import java.time.LocalDateTime;

import com.isfa.promoter.entities.StockAssignment;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
public class StockAssignmentRequest extends BaseRequest {

	private Long transId;
    
    private Long storeId;
    
    private Long productId;
    
    private Long transUnit;
    
    private String transType;
    
    private String transactionType;
    
    private Double price;

    private Double totalPrice;
    
    private LocalDateTime createdDate;
    
    private String createdBy;
    
    private LocalDateTime modifiedDate;
    
    private String modifiedBy;
	
	
    public  StockAssignment convertInto() {
    	
    	StockAssignment assignment = new StockAssignment();
    	
    	assignment.setTransId(this.transId);
    	assignment.setStoreId(this.storeId);
    	assignment.setProductId(this.productId);
    	assignment.setTransUnit(this.transUnit);
    	assignment.setTransType(this.transType);
    	assignment.setTransactionType(this.transactionType);
    	assignment.setPrice(this.price);
    	assignment.setTotalPrice(this.totalPrice);
    	assignment.setCreatedDate(this.createdDate);
    	assignment.setCreatedBy(this.createdBy);
    	assignment.setModifiedDate(this.modifiedDate);
    	assignment.setModifiedBy(this.modifiedBy);
    	
    	return assignment;
    }
    
    //no-args
    public StockAssignmentRequest() {
		// TODO Auto-generated constructor stub
	}

    
    //parameterized cons
	public StockAssignmentRequest(Long transId, Long storeId, Long productId, Long transUnit, String transType,
			String transactionType, Double price,Double totalPrice, LocalDateTime createdDate, String createdBy,
			LocalDateTime modifiedDate, String modifiedBy) {
		super();
		this.transId = transId;
		this.storeId = storeId;
		this.productId = productId;
		this.transUnit = transUnit;
		this.transType = transType;
		this.transactionType = transactionType;
		this.price = price;
		this.totalPrice=totalPrice;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
	}
    
    
    
}
