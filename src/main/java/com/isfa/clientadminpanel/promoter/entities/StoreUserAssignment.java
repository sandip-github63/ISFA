package com.isfa.clientadminpanel.promoter.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "store_user_assignment")
public class StoreUserAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "store_id")
    private Long storeId;
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "fromdate")
    private LocalDate fromDate;
    
    @Column(name = "todate")
    private LocalDate toDate;
    
    @Column(name = "date_created",updatable = false)
    private LocalDateTime dateCreated;
    
    @Column(name = "reason")
    private String reason;
    
    @Column(name = "campaign_id")
    private Long campaignId;
    
    @Column(name = "modified_by")
    private String modifiedBy;
    
    @Column(name = "created_by",updatable = false)
    private String createdBy;
    
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    // constructors, getters and setters
}

