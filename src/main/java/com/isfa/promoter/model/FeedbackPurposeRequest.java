package com.isfa.promoter.model;

import com.isfa.promoter.entities.FeedbackPurpose;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackPurposeRequest {

    private Long id;
    
    private String name;

    public static FeedbackPurpose convertInto(FeedbackPurposeRequest request) {
    	
    	FeedbackPurpose purpose = new FeedbackPurpose();
    	
    	purpose.setId(request.getId());
    	purpose.setName(request.getName());
    	
    	return purpose;
    }
    
}

