package com.isfa.clientadminpanel.promoter.response;

import java.util.ArrayList;
import java.util.List;
import com.isfa.clientadminpanel.promoter.entities.StoreUserAssignment;
import com.isfa.models.User;
import com.isfa.promoter.dao.StoreDetailRepository;
import com.isfa.promoter.entities.StoreDetail;
import com.isfa.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class StoreUserAssignmentResponse extends BaseResponse<StoreUserAssignmentResponse> {

//	@Autowired
	StoreDetailRepository storeDetailRepository;
//	@Autowired
	UserRepository userRepository;

	private Long storeId;
	private Long userId;
	private String storeName;
	private String userName;
	private String fromDate;
	private String toDate;
	private Long campaignId;

	public StoreUserAssignmentResponse(StoreDetailRepository storeDetailRepository, UserRepository userRepository) {
		this.storeDetailRepository = storeDetailRepository;
		this.userRepository = userRepository;
	}

	public BaseResponse<StoreUserAssignmentResponse> convert(StoreUserAssignment assignment) {

		BaseResponse<StoreUserAssignmentResponse> bResp = new BaseResponse<>();
		StoreUserAssignmentResponse response = new StoreUserAssignmentResponse();

		response.setCampaignId(assignment.getCampaignId());
		response.setUserId(assignment.getUserId());
		response.setStoreId(assignment.getStoreId());
		response.setFromDate(assignment.getFromDate().toString());
		response.setToDate(assignment.getToDate().toString());

		if (assignment.getStoreId() != null) {
			StoreDetail storeDetail = storeDetailRepository.findById(assignment.getStoreId()).orElse(null);
			if (storeDetail != null) {
				response.setStoreName(storeDetail.getStoreName());
			}
		}
		if (assignment.getUserId() != null) {
			User user = userRepository.findById(assignment.getUserId()).orElse(null);
			if (user != null) {
				response.setUserName(user.getUsername());
			}
		}
		bResp.setData(response);
		return bResp;
	}

	public BaseResponse<StoreUserAssignmentResponse> convertList(List<StoreUserAssignment> storeUserList) {
		System.out.println(storeDetailRepository);
		System.out.println(userRepository);
		BaseResponse<StoreUserAssignmentResponse> bResp = new BaseResponse<>();
		List<StoreUserAssignmentResponse> respList = new ArrayList<>();

		if (storeUserList.isEmpty()) {
			bResp.setDataList(respList);
			return bResp;
		}

		for (StoreUserAssignment assignment : storeUserList) {

			StoreUserAssignmentResponse response = new StoreUserAssignmentResponse();

			response.setCampaignId(assignment.getCampaignId());
			response.setUserId(assignment.getUserId());
			response.setStoreId(assignment.getStoreId());
			response.setFromDate(assignment.getFromDate().toString());
			response.setToDate(assignment.getToDate().toString());

			StoreDetail storeDetail = storeDetailRepository.findById(assignment.getStoreId()).orElse(null);
			if (storeDetail != null) {
				response.setStoreName(storeDetail.getStoreName());
			}

			User user = userRepository.findById(assignment.getUserId()).orElse(null);
			if (user != null) {
				response.setUserName(user.getUsername());
			}

			respList.add(response);
		}
		bResp.setDataList(respList);
		return bResp;

	}

}
