	package com.isfa.clientadminpanel.leave.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.isfa.Exception.ResourceNotFoundException;
import com.isfa.clientadminpanel.leave.dao.LeaveTypeRepository;
import com.isfa.clientadminpanel.leave.entities.LeaveType;
import com.isfa.clientadminpanel.leave.model.LeaveTypeRequest;
import com.isfa.clientadminpanel.leave.response.BaseResponse;
import com.isfa.clientadminpanel.leave.response.LeaveTypeDTO;
import com.isfa.clientadminpanel.leave.response.LeaveTypeResponse;
import com.isfa.models.User;
import com.isfa.repository.UserRepository;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

	public static final Logger logger = LoggerFactory.getLogger(LeaveTypeServiceImpl.class);
	
	@Autowired
	LeaveTypeRepository leaveTypeRepository;
	
	@Autowired
	UserRepository userRepository;
	
//	@Autowired
//	StorageService storageService;
	
	@Value("${project.image}")
	private String path;
	
	
	@Override
	public ResponseEntity<?> getAllLeaves(){
		logger.info("LeaveType Service getting All LeaveType executing");
		logger.info("LeaveType Repository getting All LeaveType executing");
		List<LeaveType> leaveTypes =  leaveTypeRepository.findAll();
		logger.info("LeaveType Repository getting All LeaveType completed");
		BaseResponse<LeaveTypeResponse> list = LeaveTypeResponse.convertList(leaveTypes);

		LeaveTypeResponse response = new LeaveTypeResponse();
		if(list.getDataList().isEmpty()) {
			logger.info("LeaveType List is empty");
        	String message = "No LeaveTypes found";  
        	list.setMessage(message);
        	list.setStatus("404");
        	logger.info("LeaveType Service getting All LeaveType completed");
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else {
        	logger.info("LeaveType List exists with some data");
        	list.setMessage("LeaveTypeList Successfully fetched");
        	list.setStatus("200");
        	logger.info("LeaveType Service getting All LeaveType completed");
        	return ResponseEntity.status(HttpStatus.OK).body(list);
        }
		
	}
	
	@Override
	public ResponseEntity<?> getLeaveById(Long id) throws IOException{
		logger.info("LeaveType Service Getting LeaveType By Id executing");
		logger.info("LeaveType Repository Getting LeaveType By Id executing");
		Optional<LeaveType> leaveType = leaveTypeRepository.findById(id);
		logger.info("LeaveType Repository Getting LeaveType By Id completed");
		if(leaveType.isPresent()) {
			logger.info("LeaveType at provided id exists ");
        	LeaveType globalLeave = leaveType.get();
        	
        	// Get the path of the icon file on the server
            String iconPath = "images/" + globalLeave.getIcon();
            Path filePath = Paths.get(iconPath).normalize();
        	
         // Check if the file exists
            if (!Files.exists(filePath)) {
                throw new ResourceNotFoundException("Leave type icon not found with ID " + id);
            }

            // Read the file contents into a byte array
            byte[] fileContent = Files.readAllBytes(filePath);
            System.out.println(fileContent);
//            BaseResponse<LeaveTypeResponse> resp = LeaveTypeResponse.convert(globalLeave);
            
         // Create a LeaveTypeDTO object with the LeaveType and icon byte array
            LeaveTypeDTO<LeaveTypeResponse> response = new LeaveTypeDTO<>();
            
            LeaveTypeResponse leaveTypeResponse = LeaveTypeResponse.convertToLeaveTypeResponse(globalLeave);
            
//        	BaseResponse<LeaveTypeDTO> response = new BaseResponse<>();
        	response.setMessage("Record Successfully Fetched");
        	response.setStatus(200);
        	response.setData(leaveTypeResponse);
        	response.setIcon(fileContent);
        	logger.info("LeaveType Service Getting LeaveType By Id completed");
        	return ResponseEntity.status(HttpStatus.OK).body(response);
        }else {
        	logger.info("LeaveType at provided id doesn't exists ");
        	BaseResponse<LeaveTypeResponse> response = new BaseResponse<>();
        	String message = "Leave Type with ID " + id + " not found";
        	response.setMessage(message);
        	response.setStatus("404");
        	logger.info("LeaveType Service Getting LeaveType By Id completed");
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
	}
	
//	@Override
//	public LeaveType createLeave(LeaveTypeRequest leaveTypeReq) {
//		logger.info("LeaveType Service Saving New Leave executing");
//		LeaveType leaveType = leaveTypeReq.toConvert();
//		
//		TmColor color = leaveType.getColor();
//		Icon icon = leaveType.getIcon();
//		if (color != null) {
//            String colorType = color.getType();
//            if (colorType != null) {
//                color = colorRepository.findByType(colorType).orElse(null);
//            }
//        }
//		 leaveType.setColor(color);
//		
//		 if (icon != null) {
//			 String iconType = icon.getIconType();
//			 if (iconType != null) {
//				 icon = iconRepository.findByIconType(iconType).orElse(null);
//			 }
//		 }
//		 leaveType.setIcon(icon);
//		 
//		LocalDateTime now = LocalDateTime.now();
//		leaveType.setCreatedDate(now);
//		leaveType.setModifiedDate(now);
//		return leaveTypeRepository.save(leaveType);
//	}
	
	@Override
	public BaseResponse<LeaveTypeResponse> createLeave(LeaveTypeRequest leaveTypeReq, MultipartFile image, Long userId) throws IOException{
		logger.info("LeaveType Service Saving New LeaveType executing");
		LeaveType leaveType = leaveTypeReq.toConvert();
		
		// image name
		String name = image.getOriginalFilename();
		logger.info("File name executed");
		
		// Full path
		String randID = UUID.randomUUID().toString().concat(LocalDateTime.now().toString());
		String originalFileName = (randID.concat(name.substring(name.lastIndexOf("."))).replace(":", "_"));
		
		String filePath = path + File.separator + originalFileName;
		
		logger.info("File full path executed");
		
		// create folder if not exists
		File f = new File(path);
		if (!f.exists()) {
			logger.info("Make directory");
			f.mkdir();
		}
		
//		String imageUrl = storageService.uploadImage(originalFileName, image.getInputStream());
//		leaveType.setIcon(imageUrl);
		
		// file copy
		Files.copy(image.getInputStream(), Paths.get(filePath));
		logger.info("File copied");
		leaveType.setIcon(originalFileName);
		
		User user = userRepository.findById(userId).orElse(null);
		
		if (user != null) {
			leaveType.setCreatedBy(user.getUsername());
			leaveType.setModifiedBy(user.getUsername());
		}
		
		LocalDateTime now = LocalDateTime.now();
		leaveType.setCreatedDate(now);
		leaveType.setModifiedDate(now);
		
		logger.info("LeaveType Repository Saving New LeaveType executing");
		System.out.println(leaveType);
		leaveType = leaveTypeRepository.save(leaveType);
		logger.info("LeaveType Repository Saving New LeaveType executing");
		BaseResponse<LeaveTypeResponse> response = LeaveTypeResponse.convert(leaveType);
		response.setMessage("Successfully Inserted");
		response.setStatus("200");
		logger.info("LeaveType Service Saving New LeaveType completed");
		return response;
	}
	
	@Override
	public ResponseEntity<?> updateLeave(Long id,LeaveTypeRequest leave,MultipartFile image,Long userId) throws IOException {
		logger.info("LeaveType Service Updating Existing LeaveType executing");
		logger.info("LeaveType Repository Updating Existing LeaveType executing");
    	Optional<LeaveType> existingLeave = leaveTypeRepository.findById(id);
    	logger.info("LeaveType Repository Getting LeaveType By Id completed");
    	if(existingLeave.isPresent()) {
    		logger.info("LeaveType exists at provided id...in order to update");
    		leave.setLeaveId(id);
    		BaseResponse<LeaveTypeResponse> response = createLeave(leave,image,userId);
    		response.setMessage("Updated Successfully");
    		response.setStatus("200");
    		logger.info("LeaveType Service Updating Existing LeaveType completed");
    		return ResponseEntity.status(HttpStatus.OK).body(response);
    	}else {
    		logger.info("LeaveType do not exists at provided id...in order to update");
    		BaseResponse<LeaveTypeResponse> response = new BaseResponse<>();
    		String message = "Leave with ID " + id + " not found to update";
    		response.setMessage(message);
    		response.setStatus("404");
    		logger.info("LeaveType Service Updating Existing LeaveType completed");
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    	}
	}
	
	
	@Override
	public ResponseEntity<?> deleteLeave(Long id) {
		logger.info("LeaveType Service Deleting LeaveType By Id executing");
		logger.info("LeaveType Repository Deleting Existing LeaveType executing");
    	Optional<LeaveType> existingLeave = leaveTypeRepository.findById(id);
    	logger.info("LeaveType Repository Getting LeaveType By Id completed");
    	if(existingLeave.isPresent()) {
    		logger.info("LeaveType exists at provided id...in order to delete");
    		this.leaveTypeRepository.deleteById(id);
    		BaseResponse<LeaveTypeResponse> response = new BaseResponse<>();
    		String message = "Leave with ID " + id + " deleted successfully";
    		response.setMessage(message);
    		response.setStatus("200");
    		logger.info("LeaveType Service Deleting Existing LeaveType completed");
    		return ResponseEntity.status(HttpStatus.OK).body(response);
    	}
    	else {
    		logger.info("LeaveType do not exists at provided id...in order to delete");
    		BaseResponse<LeaveTypeResponse> response = new BaseResponse<>();
    		String message = "Leave with ID " + id + " not found to delete";
    		response.setMessage(message);
    		response.setStatus("404");
    		logger.info("LeaveType Service Deleting Existing LeaveType completed");
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    	}
		
	}
}
