package com.isfa.promoter.model;

import java.time.LocalDateTime;

import com.isfa.promoter.entities.StockBalance;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
public class StockBalanceRequest extends BaseRequest {

	private Long id;

    private Long storeId;

    private Long productId;

    private Long balance;

    private Double totalPrice;

    private LocalDateTime createdDate;

    private String createdBy;

    private LocalDateTime modifiedDate;

    private String modifiedBy;

    
  //no-args
    public StockBalanceRequest() {
 	// TODO Auto-generated constructor stub
    }

    
    //parameterized cons
 	public StockBalanceRequest(Long id, Long storeId, Long productId, Long balance, Double totalPrice,
 			LocalDateTime createdDate, String createdBy, LocalDateTime modifiedDate, String modifiedBy) {
 		super();
 		this.id = id;
 		this.storeId = storeId;
 		this.productId = productId;
 		this.balance = balance;
 		this.totalPrice = totalPrice;
 		this.createdDate = createdDate;
 		this.createdBy = createdBy;
 		this.modifiedDate = modifiedDate;
 		this.modifiedBy = modifiedBy;
 	}
 	
    
    public static StockBalance convertInto(StockBalanceRequest request) {
    	
    	StockBalance balance = new StockBalance();
    	
    	balance.setId(request.getId());
    	balance.setStoreId(request.getStoreId());
    	balance.setProductId(request.getProductId());
    	balance.setBalance(request.getBalance());
    	balance.setTotalPrice(request.getTotalPrice());
    	balance.setCreatedDate(request.getCreatedDate());
    	balance.setCreatedBy(request.getCreatedBy());
    	balance.setModifiedDate(request.getModifiedDate());
    	balance.setModifiedBy(request.getModifiedBy());
    	
    	return balance;
    }
	
}
