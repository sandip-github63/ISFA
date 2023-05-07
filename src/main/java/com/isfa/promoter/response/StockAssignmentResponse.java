package com.isfa.promoter.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.isfa.promoter.entities.StockAssignment;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
public class StockAssignmentResponse {

    
    private Long transUnit;
    
    private String transType;
    
    private String transactionType;
    
    private Double price;

    private Double totalPrice;
    
    
    public static BaseResponse<StockAssignmentResponse> convert(StockAssignment assignment){
    	
    	BaseResponse<StockAssignmentResponse> bResp = new BaseResponse<>();
    	StockAssignmentResponse response = new StockAssignmentResponse();
    	
    	response.setTransUnit(assignment.getTransUnit());
    	response.setTransType(assignment.getTransType());
    	response.setTransactionType(assignment.getTransactionType());
    	response.setPrice(assignment.getPrice());
    	response.setTotalPrice(assignment.getTotalPrice());
    	
    	bResp.setData(response);
    	
    	return bResp;
    }
    
    
public static BaseResponse<StockAssignmentResponse> convertList(List<StockAssignment> list){
    	
    	List<StockAssignmentResponse> respList = new ArrayList<>();
    	BaseResponse<StockAssignmentResponse> bResp = new BaseResponse<>();
    	
    	if(list.isEmpty()) {
    		bResp.setDataList(respList);
    		return bResp;
    	}
    	
    	for(StockAssignment assignment:list) {
    		
    		StockAssignmentResponse response = new StockAssignmentResponse();
    		
    		response.setTransUnit(assignment.getTransUnit());
        	response.setTransType(assignment.getTransType());
        	response.setTransactionType(assignment.getTransactionType());
        	response.setPrice(assignment.getPrice());
        	response.setTotalPrice(assignment.getTotalPrice());
        	
    		respList.add(response);
    		
    	}
    	bResp.setDataList(respList);
    	return bResp;
    }
	
}
