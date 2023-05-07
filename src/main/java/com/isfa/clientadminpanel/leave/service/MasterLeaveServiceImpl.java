package com.isfa.clientadminpanel.leave.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.isfa.clientadminpanel.leave.dao.MasterLeaveRepository;
import com.isfa.clientadminpanel.leave.entities.MasterLeave;
import com.isfa.clientadminpanel.leave.model.MasterLeaveRequest;
import com.isfa.clientadminpanel.leave.response.BaseResponse;
import com.isfa.clientadminpanel.leave.response.MasterLeaveResponse;

@Service
public class MasterLeaveServiceImpl implements MasterLeaveService{

	public static final Logger logger = LoggerFactory.getLogger(MasterLeaveServiceImpl.class);
	
		@Autowired
	    private MasterLeaveRepository myRepository;

	    @Override
	    public ResponseEntity<?> findAllLeaves() {
	    	logger.info("MasterLeave Service getting All Leaves executing");
	    	logger.info("MasterLeave Repository getting All Leaves executing");
	        List<MasterLeave> myLeaves = myRepository.findAll();
	        logger.info("MasterLeave Repository getting All Leaves completed");
	        BaseResponse<MasterLeaveResponse> list = MasterLeaveResponse.convertList(myLeaves);
	        if(list.getDataList().isEmpty()) {
	        	logger.info("MasterLeave List is empty");
	        	MasterLeaveResponse response = new MasterLeaveResponse();
	        	response.setMessage("No leaves found");
	        	list.setStatus("404");
	        	logger.info("MasterLeave Service Getting All Leaves completed");
	        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(list);
	        }
	        logger.info("Leaves List exists with some data");
	        list.setMessage("MasterLeave List successfully fetched");
			list.setStatus("200");
	        logger.info("MasterLeave Service Getting All Leaves completed");
	        return ResponseEntity.status(HttpStatus.OK).body(list);
	    }

	    @Override
	    public ResponseEntity<?> findLeaveById(Long id) {
	    	logger.info("MasterLeave Service Getting Leave By Id executing");
	    	logger.info("MasterLeave Repository Getting Leave By Id executing");
	    	Optional<MasterLeave> leave = myRepository.findById(id);
	    	logger.info("MasterLeave Repository Getting Leave By Id completed");
	    	if (leave.isPresent()) {
	    		logger.info("Leave at provided id exists ");
	    		BaseResponse<MasterLeaveResponse> response = MasterLeaveResponse.convert(leave.get());
	    		response.setMessage("Record successfully fetched");
	    		response.setStatus("200");
	    		logger.info("MasterLeave Service Getting Leave By Id completed");
	    		return ResponseEntity.status(HttpStatus.OK).body(response);
	        } else {
	        	logger.info("Leave at provided id doesn't exists ");
	        	BaseResponse<MasterLeaveResponse> response = new BaseResponse<>();
	        	String message = "Leave with ID " + id + " not found";
	        	response.setMessage(message);
	        	response.setStatus("404");
	        	logger.info("MasterLeave Service Getting Leave By Id completed");
	        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
	    }

	    @Override
	    public BaseResponse<MasterLeaveResponse> saveLeave(MasterLeaveRequest masterLeaveRequest) {
	    	logger.info("MasterLeave Service Saving New Leave executing");
	    	MasterLeave leave = masterLeaveRequest.toConvert();
	    	LocalDateTime time = LocalDateTime.now();
	        leave.setCreatedDate(time);
	        leave.setModifiedDate(time);
	        logger.info("MasterLeave Repository Saving New Leave executing");
	        MasterLeave msLeave = myRepository.save(leave);
	        logger.info("MasterLeave Repository Saving New Leave completed");
	        BaseResponse<MasterLeaveResponse> response = MasterLeaveResponse.convert(msLeave);
	        logger.info("MasterLeave Service Saving New Leave completed");
	        response.setMessage("Record Successfully Inseted");
	        response.setStatus("200");
	        return response;
	    }

	    @Override
	    public ResponseEntity<?> updateLeave(Long id,MasterLeaveRequest request){
	    	logger.info("MasterLeave Service Updating existing Leave executing");
	    	logger.info("MasterLeave Repository Updating existing Leave executing");
	    	Optional<MasterLeave> existingLeave = myRepository.findById(id);
	    	logger.info("MasterLeave Repository Updating existing Leave completed");
	    	if (existingLeave.isPresent()) {
	    		logger.info("Leave exists at provided id...in order to update");
	    		request.setDayId(id);
	    		BaseResponse<MasterLeaveResponse> response = saveLeave(request);
	    		response.setMessage("Record Successfully Updated");
	    		response.setStatus("200");
	    		logger.info("MasterLeave Service Updating Existing Leave completed");
	    		return ResponseEntity.status(HttpStatus.OK).body(response);
	    	} else {
	    		logger.info("Leave do not exists at provided id...in order to update");
	    		BaseResponse<MasterLeaveResponse> response = new BaseResponse<>();
	    		String message = "Leave with ID " + id + " not found to update";
	    		response.setMessage(message);
	    		response.setStatus("404");
	    		logger.info("MasterLeave Controller Updating Existing Leave completed");
	    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    	}
	    }

	    @Override
	    public ResponseEntity<?> deleteLeave(Long id) {
	    	logger.info("MasterLeave Service Deleting Leave By Id executing");
	    	logger.info("MasterLeave Repository Deleting Leave By Id executing");
	    	Optional<MasterLeave> existingLeave = myRepository.findById(id);
	    	logger.info("MasterLeave Repository Deleting Leave By Id completed");
	    	BaseResponse<MasterLeaveResponse> response = new BaseResponse<>();
	    	if(existingLeave.isPresent()) {
	    		logger.info("Leave exists at provided id...in order to delete");
	    		myRepository.deleteById(id);
	        	String message = "Icon with ID " + id + " deleted successfully";
	        	response.setMessage(message);
	        	response.setStatus("200");
	        	logger.info("MasterLeave Service Deleting Existing Leave completed");
	        	return ResponseEntity.status(HttpStatus.OK).body(response);
	        }else {
	        	logger.info("Leave do not exists at provided id...in order to delete");
	        	String message = "Icon with ID " + id + " not found to delete";
	        	response.setMessage(message);
	        	response.setStatus("404");
	        	logger.info("MasterLeave Service Deleting Existing Leave completed");
	        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
	    }
	
}
