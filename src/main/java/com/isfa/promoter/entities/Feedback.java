package com.isfa.promoter.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "feedback")
public class Feedback {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="feedback_id")
    private Long id;
    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "purpose_id")
    @Column(name="purpose_id")
    private Long purposeId;
    
    @Column(name = "reason", nullable = false)
    private String reason;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @Column(name = "created_date",updatable = false)
    private LocalDateTime createdDate;
    
    @Column(name = "created_by",updatable = false)
    private String createdBy;
    
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;
    
    @Column(name = "modified_by")
    private String modifiedBy;
    
}
