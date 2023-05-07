package com.isfa.promoter.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.isfa.promoter.entities.FeedbackPurpose;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
public class FeedbackPurposeResponse extends BaseResponse<FeedbackPurposeResponse> {

	private Long id;

	private String name;
	
	public static BaseResponse<FeedbackPurposeResponse> convertList(List<FeedbackPurpose> list){
		
		List<FeedbackPurposeResponse> respList = new ArrayList<>();
		BaseResponse<FeedbackPurposeResponse> bResp = new BaseResponse<>();
		
		if(list.isEmpty()) {
			bResp.setDataList(respList);
			return bResp;
		}
		
		for(FeedbackPurpose purpose : list) {
			
			FeedbackPurposeResponse resp = new FeedbackPurposeResponse();
			resp.setId(purpose.getId());
			resp.setName(purpose.getName());
		
			respList.add(resp);
		}
		bResp.setDataList(respList);
		return bResp;
	}

}
