package com.isfa.leave.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.isfa.clientadminpanel.leave.dao.LeaveTypeRepository;
import com.isfa.clientadminpanel.leave.entities.LeaveType;
import com.isfa.leave.dao.LeaveBalanceInfoRepository;
import com.isfa.leave.entities.LeaveBalanceInfo;
import com.isfa.leave.model.LeaveBalanceInfoRequest;
import com.isfa.leave.response.BaseResponse;
import com.isfa.leave.response.LeaveBalanceInfoResponse;

@Service
public class LeaveBalanceInfoServiceImpl implements LeaveBalanceInfoService {

	public static final Logger logger = LoggerFactory.getLogger(LeaveBalanceInfoServiceImpl.class);
	
	
	@Autowired
	private LeaveBalanceInfoRepository myRepository;
	
	@Autowired
	private LeaveTypeRepository leaveTypeRepository;
	
	@Override
	public ResponseEntity<?> findAllLeaveBalance() {
		logger.info("LeaveBalanceInfo Service getting All LeaveBalanceInfo executing");
		BaseResponse<LeaveBalanceInfoResponse> resp = new LeaveBalanceInfoResponse();
		logger.info("LeaveBalanceInfo Repository getting All LeaveBalanceInfo executing");
		List<LeaveBalanceInfo> list = myRepository.findAll();
		logger.info("LeaveBalanceInfo Repository getting All LeaveBalanceInfo completed");
		BaseResponse<LeaveBalanceInfoResponse> respList = LeaveBalanceInfoResponse.convertList(list);
		
		if(respList.getDataList().isEmpty()) {
			logger.info("LeaveBalanceInfo List is empty");
			 String message = "No leave balance found";
			 resp.setMessage(message);
			 respList.setStatus("404");
			 logger.info("LeaveBalanceInfo Service getting All LeaveBalanceInfo completed");
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respList);
		}
		logger.info("LeaveBalanceInfo List exists with some data");
		respList.setMessage("Color List successfully fetched");
		respList.setStatus("200");
		logger.info("LeaveBalanceInfo Service getting All LeaveBalanceInfo completed");
		return ResponseEntity.status(HttpStatus.OK).body(respList);
	}

	@Override
	public ResponseEntity<?> findByLeaveBalanceId(Long id) {
		logger.info("LeaveBalanceInfo Service Getting LeaveBalanceInfo By Id executing");
		logger.info("LeaveBalanceInfo Repository Getting LeaveBalanceInfo By Id executing");
		Optional<LeaveBalanceInfo> info = myRepository.findById(id);
		logger.info("LeaveBalanceInfo Repository Getting LeaveBalanceInfo By Id completed");
		if(info.isPresent()) {
			logger.info("LeaveBalanceInfo at provided id exists ");
			BaseResponse<LeaveBalanceInfoResponse> response = LeaveBalanceInfoResponse.convert(info.get());
			response.setMessage("Record successfully fetched");
			response.setStatus("200");
			logger.info("LeaveBalanceInfo Service Getting LeaveBalanceInfo By Id completed");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			logger.info("LeaveBalanceInfo at provided id doesn't exists ");
			BaseResponse<LeaveBalanceInfoResponse> response = new LeaveBalanceInfoResponse();
			String message = "LeaveBalanceInfo with ID " + id + " not found";
			response.setMessage(message);
			response.setStatus("404");
			logger.info("LeaveBalanceInfo Service Getting LeaveBalanceInfo By Id completed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		
		}
	}

	@Override
	public BaseResponse<LeaveBalanceInfoResponse> saveLeaveBalance(LeaveBalanceInfoRequest leaveBalanceInfoRequest) {
		logger.info("LeaveBalanceInfo Service Saving New LeaveBalanceInfo executing");
		LocalDateTime now = LocalDateTime.now();
		
		LeaveType type = leaveTypeRepository.findById(leaveBalanceInfoRequest.getLeaveId()).orElse(null);
		
		if(type!=null) {
			leaveBalanceInfoRequest.setTotalAllocated(type.getYearlyBalance());
		}
		leaveBalanceInfoRequest.setYearOfBalance(now.getYear());
		leaveBalanceInfoRequest.setCreatedDate(now);
		leaveBalanceInfoRequest.setModifiedDate(now);
		
		LeaveBalanceInfo info = leaveBalanceInfoRequest.convertInto();
		logger.info("LeaveBalanceInfo Repository Saving New LeaveBalanceInfo executing");
		info = myRepository.save(info);
		logger.info("LeaveBalanceInfo Repository Saving New LeaveBalanceInfo completed");
		BaseResponse<LeaveBalanceInfoResponse> response = LeaveBalanceInfoResponse.convert(info);
		response.setMessage("Record successfully Inserted");
		response.setStatus("200");
		logger.info("LeaveBalanceInfo Service Saving New LeaveBalanceInfo completed");
		
		return response;
	}

	@Override
	public ResponseEntity<?> updateLeaveBalance(Long id, LeaveBalanceInfoRequest leaveBalanceInfoRequest) {
		logger.info("LeaveBalanceInfo Service Updating Existing LeaveBalanceInfo executing");
		logger.info("LeaveBalanceInfo Repository Updating Existing LeaveBalanceInfo executing");
		Optional<LeaveBalanceInfo> existingInfo = myRepository.findById(id);
		logger.info("LeaveBalanceInfo Repository Updating Existing LeaveBalanceInfo completed");
		if(existingInfo.isPresent()) {
			logger.info("LeaveBalanceInfo exists at provided id...in order to update");
			leaveBalanceInfoRequest.setLeaveBalanceId(id);
			BaseResponse<LeaveBalanceInfoResponse> response = saveLeaveBalance(leaveBalanceInfoRequest);
			response.setMessage("Record Successfully Updated");
			response.setStatus("200");
			logger.info("LeaveBalanceInfo Service Updating Existing LeaveBalanceInfo completed");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
		}else {
			logger.info("LeaveBalanceInfo does not exists at provided id...in order to update");
			BaseResponse<LeaveBalanceInfoResponse> response = new LeaveBalanceInfoResponse();
			String message = "LeaveBalanceInfo with ID " + id + " not found to update";
			response.setMessage(message);
			response.setStatus("404");
			logger.info("LeaveBalanceInfo Service Updating Existing LeaveBalanceInfo completed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
	}

	@Override
	public ResponseEntity<?> deleteLeaveBalanceById(Long id) {
		logger.info("LeaveBalanceInfo Service Deleting LeaveBalanceInfo By Id executing");
		logger.info("LeaveBalanceInfo Repository Deleting LeaveBalanceInfo By Id executing");
		Optional<LeaveBalanceInfo> existingInfo = myRepository.findById(id);
		logger.info("LeaveBalanceInfo Repository Deleting LeaveBalanceInfo By Id completed");
		BaseResponse<LeaveBalanceInfoResponse> response = new LeaveBalanceInfoResponse();
		
		if(existingInfo.isPresent()) {
			logger.info("LeaveBalanceInfo exists at provided id...in order to delete");
			logger.info("LeaveBalanceInfo Repository Deleting LeaveBalanceInfo By Id executing");
			myRepository.deleteById(id);
			logger.info("LeaveBalanceInfo Repository Deleting LeaveBalanceInfo By Id completed");
			String message = "LeaveBalanceInfo" + " with ID " + id + " deleted successfully";
			response.setMessage(message);
			response.setStatus("200");
			logger.info("LeaveBalanceInfo Service Deleting LeaveBalanceInfo By Id completed");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
		}else {
			logger.info("LeaveBalanceInfo does not exists at provided id...in order to delete");
			String message = "LeaveBalanceInfo" + " with ID " + id + " not found to delete";
			response.setMessage(message);
			response.setStatus("404");
			logger.info("LeaveBalanceInfo Service Deleting LeaveBalanceInfo By Id completed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

}
