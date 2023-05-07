package com.isfa.promoter.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.isfa.promoter.entities.StockBalance;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
public class StockBalanceResponse extends BaseResponse<StockBalanceResponse> {

	private Long balance;

    private Double totalPrice;

    
    public static BaseResponse<StockBalanceResponse> convert(StockBalance balance){
    	
    	BaseResponse<StockBalanceResponse> bResp = new BaseResponse<>();
    	StockBalanceResponse response = new StockBalanceResponse();
    	
    	response.setBalance(balance.getBalance());
    	response.setTotalPrice(balance.getTotalPrice());
    	
    	bResp.setData(response);
    	
    	return bResp;
    }
    
    
    public static BaseResponse<StockBalanceResponse> convertList(List<StockBalance> list){
    	
    	List<StockBalanceResponse> respList = new ArrayList<>();
    	BaseResponse<StockBalanceResponse> bResp = new BaseResponse<>();
    	
    	if(list.isEmpty()) {
    		bResp.setDataList(respList);
    		return bResp;
    	}
    	
    	for(StockBalance balance:list) {
    		
    		StockBalanceResponse resp = new StockBalanceResponse();
    		
    		resp.setBalance(balance.getBalance());
    		resp.setTotalPrice(balance.getTotalPrice());
    		
    		respList.add(resp);
    		
    	}
    	bResp.setDataList(respList);
    	return bResp;
    }
	
}
