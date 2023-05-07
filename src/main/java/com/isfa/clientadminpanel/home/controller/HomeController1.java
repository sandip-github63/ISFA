package com.isfa.clientadminpanel.home.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.isfa.clientadminpanel.home.entity.HomePageField;
import com.isfa.clientadminpanel.home.entity.HomePageFieldRequest;
import com.isfa.clientadminpanel.home.repository.HomePageFieldRepository;
import com.isfa.clientadminpanel.home.service.HomePageService;
import com.isfa.clientadminpanel.promoter.dao.StoreUserAssignmentRepository;
import com.isfa.clientadminpanel.promoter.entities.StoreUserAssignment;
import com.isfa.clientadminpanel.promoter.service.StoreUserAssignmentServiceImpl;
import com.isfa.models.User;
import com.isfa.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class HomeController1 {
    
	@Autowired
    private HomePageService homePageService;
	
	@Autowired
	private StoreUserAssignmentRepository storeUserAssignmentRepository;
	
	@Autowired 
	private HomePageFieldRepository homePageFieldRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private StoreUserAssignmentServiceImpl storeUserAssignmentService;

	@GetMapping("/home")
	public ResponseEntity<Map<String, Object>> home(HttpSession session) {
	User user = (User) session.getAttribute("user");
	if (user != null) {
	// Construct user info object
	Map<String, String> userInfo = new HashMap<>();
	userInfo.put("user_name", user.getUsername());
	userInfo.put("email", user.getEmail());
	userInfo.put("role", user.getiRole());
	userInfo.put("phone_no", user.getMobile());
    // Construct menu objects
    List<Map<String, Object>> menu = new ArrayList<>();
    
    // Attendance menu
    Map<String, Object> attendanceMenu = new HashMap<>();
    attendanceMenu.put("name", "Attendance");
    attendanceMenu.put("key", "attendance");
    attendanceMenu.put("icon", "https://example.com/icon_image/attendance.png");
    attendanceMenu.put("isActive", true);
    menu.add(attendanceMenu);
    
    // Leave menu
    Map<String, Object> leaveMenu = new HashMap<>();
    leaveMenu.put("name", "Leave");
    leaveMenu.put("key", "leave");
    leaveMenu.put("icon", "https://example.com/icon_image/leave.png");
    leaveMenu.put("isActive", true);
    menu.add(leaveMenu);
    
    // Promoter menu
    Map<String, Object> promoterMenu = new HashMap<>();
    promoterMenu.put("name", "Promoter");
    promoterMenu.put("key", "promoter");
    promoterMenu.put("icon", "https://example.com/icon_image/promoter.png");
    promoterMenu.put("isActive", true);
    menu.add(promoterMenu);
    
    // Construct response object
    Map<String, Object> response = new HashMap<>();
    response.put("status", 200);
    response.put("message", "home page retrived successfully.");
    Map<String, Object> data = new HashMap<>();
    data.put("user_info", userInfo);
    data.put("menu", menu);
    response.put("data", data);
    
    return ResponseEntity.ok(response);
} else {
    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.UNAUTHORIZED.value());
    response.put("message", "You are not logged in.");
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
}}


	@GetMapping("/attendance")
	public ResponseEntity<Map<String, Object>> getAttendancePage() {
	    Map<String, Object> response = new HashMap<>();
	    response.put("status", "200");
	    response.put("message", "Attendance page retrieved successfully.");
	    response.put("data", "/api/attendance");
	    return ResponseEntity.ok(response);
	}

	@GetMapping("/leave")
	public ResponseEntity<Map<String, Object>> getLeavePage() {
	    Map<String, Object> response = new HashMap<>();
	    response.put("status", "200");
	    response.put("message", "Leave page retrieved successfully.");
	    response.put("data", "/api/leave");
	    return ResponseEntity.ok(response);
	}

	/**@PostMapping("/logout")
	public ResponseEntity<Map<String, Object>> logoutUser() {
	    SecurityContextHolder.clearContext();
	    Map<String, Object> response = new HashMap<>();
	    response.put("status", "success");
	    response.put("message", "User logged out successfully!");
	    response.put("data", "");
	    return ResponseEntity.ok(response);*/
	//}
	
	
	@GetMapping("/home/{id}")
	public ResponseEntity<Map<String, Object>> home(@PathVariable Long id) {
		 Optional<User> optionalUser = userRepository.findById(id);
		    if (optionalUser.isPresent()) {
		        User user = optionalUser.get();
		        // Construct user info object
		        Map<String, Object> userInfo = new HashMap<>();
		        userInfo.put("id", user.getId());
		        userInfo.put("username", user.getUsername());
		        userInfo.put("email", user.getEmail());
		        userInfo.put("password", user.getPassword());
		        userInfo.put("resetPasswordOTP", user.getResetPasswordOTP());
		        userInfo.put("supervisor", user.getSupervisor());
		        userInfo.put("companyId", user.getCompanyId());
		        userInfo.put("designation", user.getDesignation());
		        userInfo.put("mobile", user.getMobile());
		        userInfo.put("iRole", user.getiRole());
		        userInfo.put("companyName", user.getCompanyName());
		        StoreUserAssignment storeUserAssignment= storeUserAssignmentRepository.findByUserIdAndCampaignId(user.getId(), user.getCompanyId()).orElse(null);
		        if (storeUserAssignment!=null)
		        {
		        	userInfo.put("storeId",storeUserAssignment.getStoreId());
		        }
		        
		        
		        
		    

		    // Construct menu objects
		    List<Map<String, Object>> menu = new ArrayList<>();

		    // Attendance menu
		    Map<String, Object> attendanceMenu = new HashMap<>();
		    attendanceMenu.put("name", "Attendance");
		    attendanceMenu.put("key", "attendance");
		    attendanceMenu.put("icon", "https://is.gd/PU41kj");
		    attendanceMenu.put("isActive", true);
		    menu.add(attendanceMenu);

		    // Leave menu
		    Map<String, Object> leaveMenu = new HashMap<>();
		    leaveMenu.put("name", "Leave");
		    leaveMenu.put("key", "leave");
		    leaveMenu.put("icon", "https://is.gd/PU41kj");
		    leaveMenu.put("isActive", true);
		    menu.add(leaveMenu);

		    // Promoter menu
		    Map<String, Object> promoterMenu = new HashMap<>();
		    promoterMenu.put("name", "Promoter");
		    promoterMenu.put("key", "promoter");
		    promoterMenu.put("icon", "https://is.gd/PU41kj");
		    promoterMenu.put("isActive", true);
		    menu.add(promoterMenu);

		    // Construct response object
		    Map<String, Object> response = new HashMap<>();
		    response.put("status", 200);
		    response.put("message", "home page retrieved successfully.");
		    Map<String, Object> data = new HashMap<>();
		    data.put("user_info", userInfo);
		    data.put("menu", menu);
		    response.put("data", data);

		    return ResponseEntity.ok(response);
		} else {
		    Map<String, Object> response = new HashMap<>();
		    response.put("status", HttpStatus.UNAUTHORIZED.value());
		    response.put("message", "User not found.");
		    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
	}


	@PostMapping("/home/save")
	public ResponseEntity<Map<String, Object>> addHomePageField(@RequestBody HomePageFieldRequest request) throws IOException {
	    // Read photo data from file
	    MultipartFile iconImage = request.getIconImage();
	    byte[] photoData = iconImage.getBytes();

	    // Create a new entity instance and set its properties
	    HomePageField field = new HomePageField();
	    field.setFieldName(request.getFieldName());
	    field.setRedirectUrl(request.getRedirectUrl());
	    field.setIconImage(photoData);

	    // Save the entity to the database
	    HomePageField savedField = homePageFieldRepository.save(field);

	    // Create a response object
	    Map<String, Object> response = new HashMap<>();
	    response.put("status", "success");
	    response.put("message", "Field added successfully");
	    response.put("data", savedField);

	    // Return a response entity with the response object
	    return ResponseEntity.ok(response);
	}
}




