package com.isfa.clientadminpanel.promoter.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.isfa.clientadminpanel.promoter.entities.StoreUserAssignment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreUserAssignmentRequest {

	private Long id;

	private Long storeId;

	private Long userId;

	private LocalDate fromDate;

	private LocalDate toDate;

	private LocalDateTime dateCreated;

	private String reason;

	private Long campaignId;

	private String modifiedBy;

	private String createdBy;

	private LocalDateTime modifiedDate;
	
	
	public static StoreUserAssignment convertInto(StoreUserAssignmentRequest request) {
		
		StoreUserAssignment assignment = new StoreUserAssignment();
		
		assignment.setId(request.getId());
		assignment.setStoreId(request.getStoreId());
		assignment.setUserId(request.getUserId());
		assignment.setFromDate(request.getFromDate());
		assignment.setToDate(request.getToDate());
		assignment.setDateCreated(request.getDateCreated());
		assignment.setReason(request.getReason());
		assignment.setCampaignId(request.getCampaignId());
		assignment.setModifiedBy(request.getModifiedBy());
		assignment.setCreatedBy(request.getCreatedBy());
		assignment.setModifiedDate(request.getModifiedDate());
		
		return assignment;
	}

}
