package com.isfa.promoter.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.isfa.promoter.model.FeedbackRequest;
import com.isfa.promoter.service.FeedbackService;

@RestController
@RequestMapping("/api")
public class FeedbackController {
    
	@Autowired
	FeedbackService feedbackService;
    
    @PostMapping("/createFeedback/{userId}")
    public ResponseEntity<?> submitFeedback(@ModelAttribute FeedbackRequest request, @RequestParam(value = "image", required = false) MultipartFile image, @PathVariable("userId") Long userId)	throws IOException {
        return feedbackService.submitFeedback(userId, request, image);
    }
    
   @GetMapping("/getFeedbackPurposes")
   public ResponseEntity<?> getPurposes(){
	   return feedbackService.getPurposes();
   }
   
   @GetMapping("/getAllFeedback")
   private ResponseEntity<?> getAllFeedback(){
	   return feedbackService.getAllFeedback();
   }
    
}
