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

import com.isfa.clientadminpanel.leave.model.IconRequest;
import com.isfa.clientadminpanel.leave.response.BaseResponse;
import com.isfa.clientadminpanel.leave.response.IconResponse;
import com.isfa.clientadminpanel.leave.service.IconService;


@RestController
@RequestMapping("/api")
public class IconController {
	
	private static final Logger logger = LoggerFactory.getLogger(IconController.class);
	
    @Autowired
    private IconService iconService;

    @GetMapping("/icons")
    public ResponseEntity<?> getAllIcons() {
    	logger.info("Icon Controller Getting All Icons executing");
         return iconService.getAllIcons();
    }

    @PostMapping("/icons")
    public ResponseEntity<?> createIcon(@RequestBody IconRequest iconRequest) {
    		logger.info("Icon Controller Saving New Icon executing");
    		BaseResponse<IconResponse> createdIcon = this.iconService.createIcon(iconRequest);
    		logger.info("Icon Service Saving New Icon completed");
   		 	logger.info("Icon Controller Saving New Icon completed");
    		return ResponseEntity.status(HttpStatus.CREATED).body(createdIcon);
    	
    }

    @GetMapping("/icons/{id}")
    public ResponseEntity<?> getIconById(@PathVariable(value = "id") Long iconId) {
    	logger.info("Icon Controller Getting Icon By Id executing");
    	return iconService.getIconById(iconId);
    }

    @PutMapping("/icons/{id}")
    public ResponseEntity<?> updateIcon(@PathVariable(value = "id") Long iconId, @RequestBody IconRequest iconDetails) {
    	logger.info("Icon Controller Updating Existing Icon executing");
    	return  iconService.updateIcon(iconId,iconDetails);
    }

    @DeleteMapping("/icons/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long iconId) {
    	logger.info("Icon Controller Deleting Existing Icon executing");
    	return this.iconService.deleteIcon(iconId);
    }

}
