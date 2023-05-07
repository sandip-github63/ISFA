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
@Table(name = "stock_balance")
public class StockBalance {

	public static final Logger logger = LoggerFactory.getLogger(StockBalance.class);
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "balance")
    private Long balance;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "created_by",updatable = false)
    private String createdBy;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "modified_by")
    private String modifiedBy;

	
    //no-args constructor
    public StockBalance() {
		super();
		logger.info("StockBalance no-args constructor executed");
    }


    //parameterized constructor
	public StockBalance(Long id, Long storeId, Long productId, Long balance, Double totalPrice,
			LocalDateTime createdDate, String createdBy, LocalDateTime modifiedDate, String modifiedBy) {
		super();
		logger.info("StockBalance parameterized constructor executing");
		this.id = id;
		this.storeId = storeId;
		this.productId = productId;
		this.balance = balance;
		this.totalPrice = totalPrice;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		logger.info("StockBalance parameterized constructor completed");
	}
}
