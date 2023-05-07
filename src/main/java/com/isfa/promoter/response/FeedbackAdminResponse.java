package com.isfa.promoter.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackAdminResponse {

	private String purpose;
	private String reason;
	private String image;
	private String userName;
	private String createdDate;
	
}
