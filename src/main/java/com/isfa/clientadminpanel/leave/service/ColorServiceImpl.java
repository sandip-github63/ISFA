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

import com.isfa.clientadminpanel.leave.dao.ColorRepository;
import com.isfa.clientadminpanel.leave.entities.Color;
import com.isfa.clientadminpanel.leave.model.ColorRequest;
import com.isfa.clientadminpanel.leave.response.BaseResponse;
import com.isfa.clientadminpanel.leave.response.ColorResponse;

@Service
public class ColorServiceImpl implements ColorService {

	public static final Logger logger = LoggerFactory.getLogger(ColorServiceImpl.class);
	
    @Autowired
    private ColorRepository tmColorRepository;

    @Override
    public ResponseEntity<?> getAllTmColors() {
    	logger.info("Color Service getting All Color executing");
    	logger.info("Color Repository getting All Color executing");
    	List<Color> color = tmColorRepository.findAll();
    	logger.info("Color Repository getting All Color completed");
    	BaseResponse<ColorResponse> respList = ColorResponse.convertList(color);
    	if(respList.getDataList().isEmpty()) {
    		logger.info("Color List is empty");
			respList.setMessage("No color found");
			respList.setStatus("404");
			logger.info("Color Service Getting All Colors completed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respList);
		}else {
			logger.info("Color List exists with some data");
			respList.setMessage("Color List successfully fetched");
			respList.setStatus("200");
			logger.info("Color Service Getting All Colors completed");
			return ResponseEntity.status(HttpStatus.OK).body(respList);
		}
    }

    @Override
    public ResponseEntity<?> getTmColorById(long id) {
    	logger.info("Color Service Getting Color By Id executing");
    	logger.info("Color Repository Getting Color By Id executing");
    	Optional<Color> tmColor = tmColorRepository.findById(id);
    	logger.info("Color Repository Getting Color By Id completed");
    	if(tmColor.isPresent()) {
    		logger.info("Color at provided id exists ");
    		BaseResponse<ColorResponse> response = ColorResponse.convert(tmColor.get()); 
    		response.setMessage("Record successfully fetched");
    		response.setStatus("200");
    		logger.info("Color Service Getting Color By Id completed");
			return ResponseEntity.status(HttpStatus.OK).body(response);  //yaha HttpStatus.CONTINUE use nhi krenge..
		}
		else {
			logger.info("Color at provided id doesn't exists");
			BaseResponse<ColorResponse> response = new BaseResponse<>();
			String message = "Color with ID " + id + " not found";
			response.setMessage(message);
			response.setStatus("404");
			logger.info("Color Service Getting Color By Id completed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
    }

    @Override
    public BaseResponse<ColorResponse> saveTmColor(ColorRequest color) {
    	logger.info("Color Service Saving New Color executing");
    			
    	LocalDateTime now = LocalDateTime.now();
    	
    	color.setCreatedDate(now);
    	color.setModifiedDate(now);
    	        
    	Color clr = color.convertInto();
    	logger.info("Color Repository Saving New Color executing");
    	clr = this.tmColorRepository.save(clr);
    	logger.info("Color Repository Saving New Color completed");
    	BaseResponse<ColorResponse> response = ColorResponse.convert(clr);
    	response.setMessage("Record successfully Inserted");
    	response.setStatus("200");
    	logger.info("Color Service Saving New Color completed");
        return response;
    }

    @Override
    public ResponseEntity<?> updateTmColor(Long id, ColorRequest tColor){
    	logger.info("Color Service Updating existing Color executing");
    	logger.info("Color Repository Updating existing Color executing");
    	Optional<Color> existingTmColor = tmColorRepository.findById(id);
    	logger.info("Color Repository Updating existing Color completed");
    	
    	if (existingTmColor.isPresent()) {
    		logger.info("Color exists at provided id...in order to update");
			tColor.setId(id);
			BaseResponse<ColorResponse> response=saveTmColor(tColor);
			response.setMessage("Record Successfully Updated");
			response.setStatus("200");
			logger.info("Color Service Updating existing Color completed");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			logger.info("Color do not exists at provided id...in order to update");
			BaseResponse<ColorResponse> response = new BaseResponse<>();
			String message = "Color with ID " + id + " not found to update";
			response.setMessage(message);
			response.setStatus("404");
			logger.info("Color Service Updating Existing Color completed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
    }
    
    @Override
    public ResponseEntity<?> deleteTmColorById(Long id) {
    	logger.info("Color Service Deleting Color By Id executing");
    	logger.info("Color Repository Deleting Color By Id executing");
    	Optional<Color> existingTmColor = tmColorRepository.findById(id);
    	logger.info("Color Repository Deleting Color By Id completed");
    	BaseResponse<ColorResponse> response = new BaseResponse<>();
    	if(existingTmColor.isPresent()) {
    		logger.info("Color exists at provided id...in order to delete");
			this.tmColorRepository.deleteById(id);
			String message = "Color" + " with ID " + id + " deleted successfully";
			response.setMessage(message);
			response.setStatus("200");
			logger.info("Color Service Deleting Existing Color completed");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else {
			logger.info("Color do not exists at provided id...in order to delete");
			String message = "Color" + " with ID " + id + " not found to delete";
			response.setMessage(message);
			response.setStatus("404");
			logger.info("Color Service Deleting Existing Color completed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
    	
    }

}
