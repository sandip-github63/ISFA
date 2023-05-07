package com.isfa.promoter.model;

import java.time.LocalDateTime;

import com.isfa.promoter.entities.Feedback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackRequest {

	private Long id;

	private Long purposeId;

	private String reason;

	private String imageUrl;

	private LocalDateTime createdDate;

	private String createdBy;

	private LocalDateTime modifiedDate;

	private String modifiedBy;

	public static Feedback convertInto(FeedbackRequest request) {

		Feedback feedback = new Feedback();

		feedback.setId(request.getId());
		feedback.setPurposeId(request.getPurposeId());
		feedback.setReason(request.getReason());
		feedback.setImageUrl(request.getImageUrl());
		feedback.setCreatedDate(request.getCreatedDate());
		feedback.setCreatedBy(request.getCreatedBy());
		feedback.setModifiedDate(request.getModifiedDate());
		feedback.setModifiedBy(request.getModifiedBy());

		return feedback;
	}

}
