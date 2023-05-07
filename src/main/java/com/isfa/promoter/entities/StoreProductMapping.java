package com.isfa.promoter.entities;

import java.time.LocalDateTime;
import javax.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "store_product")
public class StoreProductMapping {

	public static final Logger logger = LoggerFactory.getLogger(StoreProductMapping.class);
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_product_id")
    private Long storeProductId;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "created_date",updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "created_by",updatable = false)
    private String createdBy;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "modified_by")
    private String modifiedBy;

	
    //no-args constructor
    public StoreProductMapping() {
		super();
		logger.info("StoreProductMapping no-args constructor executed");
	}
    
    
    //parameterized constructor

	public StoreProductMapping(Long storeProductId, Long storeId, Long productId, LocalDateTime createdDate,
			String createdBy, LocalDateTime modifiedDate, String modifiedBy) {
		super();
		logger.info("StoreProductMapping parameterized constructor executing");
		this.storeProductId = storeProductId;
		this.storeId = storeId;
		this.productId = productId;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		logger.info("StoreProductMapping parameterized constructor completed");
	}

}

