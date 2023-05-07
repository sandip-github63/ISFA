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

import com.isfa.clientadminpanel.leave.dao.IconRepository;
import com.isfa.clientadminpanel.leave.entities.Icon;
import com.isfa.clientadminpanel.leave.model.IconRequest;
import com.isfa.clientadminpanel.leave.response.BaseResponse;
import com.isfa.clientadminpanel.leave.response.IconResponse;

//IconService.java
@Service
public class IconServiceImpl implements IconService {

		public static final Logger logger = LoggerFactory.getLogger(IconServiceImpl.class);
	
		@Autowired
		IconRepository iconRepository;
	
		@Override
	    public ResponseEntity<?> getAllIcons() {
			logger.info("Icon Service getting All Icon executing");
			logger.info("Icon Repository getting All Icon executing");
			List<Icon> icons = iconRepository.findAll();
			logger.info("Icon Repository getting All Icon completed");
			BaseResponse<IconResponse> respList = IconResponse.convertList(icons);
			
			if(respList.getDataList().isEmpty()) {
				logger.info("Icon List is empty");
	        	respList.setMessage("No icons found");
	        	respList.setStatus("404");
	        	logger.info("Icon Service getting All Icon completed");
	        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respList);
	 		}
			logger.info("Icon List exists with some data");
			respList.setMessage("Icon List successfully fetched");
			respList.setStatus("200");
			logger.info("Icon Service getting All Icon completed");
	 		return ResponseEntity.status(HttpStatus.OK).body(respList);
	    }

	    @Override
	    public ResponseEntity<?> getIconById(Long iconId){
	    	logger.info("Icon Service Getting Icon By Id executing");
	    	logger.info("Icon Repository Getting Icon By Id executing");
	        Optional<Icon> icon = iconRepository.findById(iconId);
	        logger.info("Icon Repository Getting Icon By Id completed");
	        if(icon.isPresent()) {
	        	logger.info("Icon at provided id exists");
	        	Icon icn = icon.get();
	        	BaseResponse<IconResponse> response = IconResponse.convert(icn);
	        	response.setMessage("Record Successfully Fetched");
	    		response.setStatus("200");
	        	logger.info("Icon Service Getting Icon By Id completed");
	    		return ResponseEntity.status(HttpStatus.OK).body(response);
	    	}
	    	else {
	    		logger.info("Icon at provided id doesn't exists ");
	    		BaseResponse<IconResponse> response = new BaseResponse<>();
	    		String message = "Icon with ID " + iconId + " not found";
	    		response.setMessage(message);
	    		response.setStatus("404");
	    		logger.info("Icon Service Getting Icon By Id completed");
	    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    	}
	    }

	    @Override
	    public BaseResponse<IconResponse> createIcon(IconRequest iconRequest) {
	    	logger.info("Icon Service Saving New Icon executing");
	    	// Set createdDate and modifiedDate to current timestamp
	    	Icon icon = iconRequest.convertInto();
	    	LocalDateTime now = LocalDateTime.now();
	        icon.setCreatedDate(now);
	        icon.setModifiedDate(now);
	        logger.info("Icon Repository Saving New Icon executing");
	        Icon globeIcon = iconRepository.save(icon);
	        logger.info("Icon Repository Saving New Icon completed");
	        BaseResponse<IconResponse> response = IconResponse.convert(globeIcon);
	        response.setMessage("Record Successfully Added");
	        response.setStatus("200");
	        logger.info("Icon Service Saving New Icon completed");
	        return response;
	    }

	    @Override
	    public ResponseEntity<?> updateIcon(Long iconId, IconRequest iconRequest) {
	    	logger.info("Icon Service Updating existing Icon executing");
	    	logger.info("Icon Repository Updating existing Icon executing");
	    	Optional<Icon> icon = iconRepository.findById(iconId);
	    	logger.info("Icon Repository Updating existing Icon completed");
	        if (icon.isPresent()) {
	        	logger.info("Icon exists at provided id...in order to update");
				iconRequest.setIconId(iconId);
				BaseResponse<IconResponse> savedIcon = createIcon(iconRequest);
				savedIcon.setMessage("Icon Successfully Update");
				savedIcon.setStatus("200");
				logger.info("Icon Service Updating Existing Icon completed");
				return ResponseEntity.status(HttpStatus.OK).body(savedIcon);
			} else {
				logger.info("Icon do not exists at provided id...in order to update");
				BaseResponse<IconResponse> response = new BaseResponse<>();
				String message = "Icon with ID " + iconId + " not found to update";
				response.setMessage(message);
				response.setStatus("404");
				logger.info("Icon Service Updating Existing Icon completed");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
	    }

	    @Override
	    public ResponseEntity<?> deleteIcon(Long iconId) {
	    	logger.info("Icon Service Deleting Icon By Id executing");
	    	logger.info("Icon Repository Deleting Icon By Id executing");
	    	Optional<Icon> existingIcon = iconRepository.findById(iconId);
	    	if(existingIcon.isPresent()) {
	    		logger.info("Icon exists at provided id...in order to delete");
	    		iconRepository.deleteById(iconId);
	    		IconResponse resp = new IconResponse();
	    		String message = "Icon with ID " + iconId + " deleted successfully";
	    		resp.setMessage(message);
	    		resp.setStatus("200");
	    		logger.info("Icon Service Deleting Existing Icon completed");
	    		return ResponseEntity.status(HttpStatus.OK).body(resp);
	    	}
	    	else {
	    		logger.info("Icon do not exists at provided id...in order to delete");
	    		BaseResponse<IconResponse> response = new BaseResponse<>();
	    		String message = "Icon with ID " + iconId + " not found to delete";
	    		response.setMessage(message);
	    		response.setStatus("404");
	    		logger.info("Icon Service Deleting Existing Icon completed");
	    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    	}
	    	
	    }
}
