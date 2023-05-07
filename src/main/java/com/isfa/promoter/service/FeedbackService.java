package com.isfa.promoter.service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.isfa.promoter.model.FeedbackRequest;

public interface FeedbackService {
    
    public ResponseEntity<?> submitFeedback(Long userId, FeedbackRequest request, MultipartFile image) throws IOException;
    
    public ResponseEntity<?> getPurposes();

    public ResponseEntity<?> getAllFeedback();
}