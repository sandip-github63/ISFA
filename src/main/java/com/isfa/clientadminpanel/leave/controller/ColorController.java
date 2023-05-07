package com.isfa.clientadminpanel.leave.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.isfa.clientadminpanel.leave.model.ColorRequest;
import com.isfa.clientadminpanel.leave.response.BaseResponse;
import com.isfa.clientadminpanel.leave.response.ColorResponse;
import com.isfa.clientadminpanel.leave.service.ColorService;


@RestController
@RequestMapping("/api")
public class ColorController {

	//private static final Logger logger = Logger.(TmColorController.class);
	private static final Logger logger = LoggerFactory.getLogger(ColorController.class);
	
	@Autowired
	private ColorService tmColorService;
	
	
	@GetMapping("/colors")
	public ResponseEntity<?> getAllTmColors() {
		logger.info("Color Controller Getting All Colors executing");
		return this.tmColorService.getAllTmColors();
	}


	@GetMapping("/colors/{id}")
	public ResponseEntity<?> getTmColorById(@PathVariable("id") Long id) {
		logger.info("Color Controller Getting Color By Id executing");
			return tmColorService.getTmColorById(id);
	}


	
	@PostMapping("/colors")
	public ResponseEntity<?> saveTmColor(@RequestBody ColorRequest tmColor) {
			logger.info("Color Controller Saving New Color executing");
			BaseResponse<ColorResponse> response = this.tmColorService.saveTmColor(tmColor);
			 logger.info("Color Service Saving New Color completed");
			 logger.info("Color Controller Saving New Color completed");
			 return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	
	@PutMapping("/colors/{id}")
	public ResponseEntity<?> updateTmColor(@PathVariable("id") Long id, @RequestBody ColorRequest tColor) {
		logger.info("Color Controller updating Existing Color executing");
		return tmColorService.updateTmColor(id,tColor);
	}


	@DeleteMapping("/colors/{id}")
	public ResponseEntity<?> deleteTmColorById(@PathVariable("id") Long id) {
		logger.info("Color Controller Deleting Existing Color executing");
		return tmColorService.deleteTmColorById(id);
	}

}



