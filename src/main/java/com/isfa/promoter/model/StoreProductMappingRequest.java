package com.isfa.promoter.model;

import java.time.LocalDateTime;

import com.isfa.promoter.entities.StoreProductMapping;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class StoreProductMappingRequest extends BaseRequest{

	private Long storeProductId;

    private Long storeId;

    private Long productId;

    private LocalDateTime createdDate;

    private String createdBy;

    private LocalDateTime modifiedDate;

    private String modifiedBy;


    public  StoreProductMapping convertInto() {
    	
    	StoreProductMapping map = new StoreProductMapping();
    	
    	map.setStoreProductId(this.storeProductId);
    	map.setStoreId(this.storeId);
    	map.setProductId(this.productId);
    	map.setCreatedDate(this.createdDate);
    	map.setCreatedBy(this.createdBy);
    	map.setModifiedDate(this.modifiedDate);
    	map.setModifiedBy(this.modifiedBy);
    	
    	return map;
    }
    
}
