package com.isfa.clientadminpanel.promoter.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.isfa.clientadminpanel.promoter.dao.StoreUserAssignmentRepository;
import com.isfa.clientadminpanel.promoter.entities.StoreUserAssignment;
import com.isfa.clientadminpanel.promoter.model.StoreUserAssignmentRequest;
import com.isfa.clientadminpanel.promoter.response.BaseResponse;
import com.isfa.clientadminpanel.promoter.response.StoreUserAssignmentResponse;
import com.isfa.models.User;
import com.isfa.promoter.dao.StoreDetailRepository;
import com.isfa.repository.UserRepository;

@Service
public class StoreUserAssignmentServiceImpl implements StoreUserAssignmentService {

	public static final Logger logger = LoggerFactory.getLogger(StoreUserAssignmentServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StoreUserAssignmentRepository userAssignmentRepository;
	
	@Autowired
	private StoreDetailRepository storeDetailRepository;

	@Override
	public ResponseEntity<?> getAllStoreUserAssignment() {

		logger.info("LeaveBalanceInfo Service getting All LeaveBalanceInfo executing");
		BaseResponse<StoreUserAssignmentResponse> resp = new BaseResponse<>();
		StoreUserAssignmentResponse response = new StoreUserAssignmentResponse(storeDetailRepository,userRepository);
		logger.info("LeaveBalanceInfo Repository getting All LeaveBalanceInfo executing");
		List<StoreUserAssignment> list = userAssignmentRepository.findAll();
		
		logger.info("LeaveBalanceInfo Repository getting All LeaveBalanceInfo completed");
		BaseResponse<StoreUserAssignmentResponse> respList = response.convertList(list);

		if (respList.getDataList().isEmpty()) {
			logger.info("LeaveBalanceInfo List is empty");
			String message = "No store-user-assignment found";
			resp.setMessage(message);
			respList.setStatus("404");
			logger.info("LeaveBalanceInfo Service getting All LeaveBalanceInfo completed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respList);
		}
		logger.info("LeaveBalanceInfo List exists with some data");
		respList.setMessage("List successfully fetched");
		respList.setStatus("200");
		logger.info("LeaveBalanceInfo Service getting All LeaveBalanceInfo completed");
		return ResponseEntity.status(HttpStatus.OK).body(respList);
	}

	@Override
	public BaseResponse<StoreUserAssignmentResponse> createStoreUserAssignment(Long userId, StoreUserAssignmentRequest request) {

		StoreUserAssignment assign = userAssignmentRepository.findByCampaignIdAndStoreId(request.getCampaignId(), request.getStoreId()).orElse(null);
		
		if(assign!=null) {
			request.setId(assign.getId());
		}
		
		LocalDateTime now = LocalDateTime.now();
		request.setDateCreated(now);
		request.setModifiedDate(now);

		StoreUserAssignment assignment = StoreUserAssignmentRequest.convertInto(request);

		User user = userRepository.findById(userId).orElse(null);
		if (user != null) {
			assignment.setModifiedBy(user.getUsername());
			assignment.setCreatedBy(user.getUsername());
		}

		assignment = userAssignmentRepository.save(assignment);

		StoreUserAssignmentResponse response = new StoreUserAssignmentResponse(storeDetailRepository,userRepository);
		BaseResponse<StoreUserAssignmentResponse> bResp = response.convert(assignment);
		bResp.setMessage("Record successfully Inserted");
		bResp.setStatus("200");
		return bResp;
	}

	@Override
	public ResponseEntity<?> updateStoreUserAssignment(Long userId, Long id, StoreUserAssignmentRequest request) {

		StoreUserAssignment assignment = userAssignmentRepository.findById(id).orElse(null);

		if (assignment != null) {
			request.setId(id);
			System.out.println(request);
			BaseResponse<StoreUserAssignmentResponse> bResp = createStoreUserAssignment(userId, request);

			bResp.setMessage("Record successfully updated");
			bResp.setStatus("200");
			
			return new ResponseEntity<>(bResp, HttpStatus.OK);
		}

		BaseResponse<StoreUserAssignmentResponse> bResp = new BaseResponse<>();
		bResp.setMessage("Record does not exists");
		bResp.setStatus("404");

		return new ResponseEntity<>(bResp, HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<?> deleteStoreUserAssignment(Long id) {
		StoreUserAssignment assignment = userAssignmentRepository.findById(id).orElse(null);
		BaseResponse<StoreUserAssignmentResponse> bResp = new BaseResponse<>();

		if (assignment != null) {
			userAssignmentRepository.deleteById(id);
			bResp.setMessage("Record successfully deleted");
			bResp.setStatus("200");

			return new ResponseEntity<>(bResp, HttpStatus.OK);
		}
		bResp.setMessage("Record does not exists");
		bResp.setStatus("404");
		return new ResponseEntity<>(bResp, HttpStatus.NOT_FOUND);
	}

}
