package com.isfa.clientadminpanel.promoter.entities;

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

@Data
@Builder
@Entity
@Table(name = "category")
public class Category {

	public static final Logger logger = LoggerFactory.getLogger(Category.class);
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "created_date",updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "created_by",updatable = false)
    private String createdBy;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "modified_by")
    private String modifiedBy;


    //no-args cons
	public Category() {
		super();
		logger.info("Category no-args constructor executed");
	}

	//parameterized cons
	public Category(Long categoryId, String categoryName, LocalDateTime createdDate, String createdBy,
			LocalDateTime modifiedDate, String modifiedBy) {
		super();
		logger.info("Category parameterized constructor executing");
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		logger.info("Category parameterized constructor completed");
	}

}
