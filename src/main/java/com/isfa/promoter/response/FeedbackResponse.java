package com.isfa.promoter.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.isfa.promoter.entities.Feedback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@EqualsAndHashCode(callSuper=false)
public class FeedbackResponse {

	private Long id;

	private Long purposeId;

	private String reason;

	private String imageUrl;

	
	public static BaseResponse<FeedbackResponse> convert(Feedback feedback) {
		
		BaseResponse<FeedbackResponse> bResp = new BaseResponse<>();
		FeedbackResponse response = new FeedbackResponse();

		response.setId(feedback.getId());
		response.setPurposeId(feedback.getPurposeId());
		response.setReason(feedback.getReason());
		response.setImageUrl(feedback.getImageUrl());
		
		bResp.setData(response);
		return bResp;
		
	}

	
	public static BaseResponse<FeedbackResponse> convertList(List<Feedback> feedbackList) {
		
		BaseResponse<FeedbackResponse> bResp = new BaseResponse<>();
		List<FeedbackResponse> respList = new ArrayList<>();
		
		if(feedbackList.isEmpty()) {
			bResp.setDataList(respList);
			return bResp;
		}
		
		for(Feedback feedback : feedbackList) {
			
			FeedbackResponse response = new FeedbackResponse();
		
			response.setId(feedback.getId());
			response.setPurposeId(feedback.getPurposeId());
			response.setReason(feedback.getReason());
			response.setImageUrl(feedback.getImageUrl());
			
			respList.add(response);
		}
		bResp.setDataList(respList);
		return bResp;
	}
	
}
