package com.isfa.promoter.entities;

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
@Table(name = "feedback_purpose")
public class FeedbackPurpose {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="purpose_id")
    private Long id;
    
    @Column(name = "purpose_text", nullable = false)
    private String name;
    
}
