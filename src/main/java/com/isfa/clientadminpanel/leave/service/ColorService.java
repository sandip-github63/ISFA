package com.isfa.clientadminpanel.leave.service;


import org.springframework.http.ResponseEntity;

import com.isfa.clientadminpanel.leave.model.ColorRequest;
import com.isfa.clientadminpanel.leave.response.BaseResponse;
import com.isfa.clientadminpanel.leave.response.ColorResponse;

public interface ColorService {
    ResponseEntity<?> getAllTmColors();
    
    ResponseEntity<?> getTmColorById(long id);
    
    BaseResponse<ColorResponse> saveTmColor(ColorRequest tmColor);
    
    ResponseEntity<?> updateTmColor(Long id, ColorRequest tColor);
    
    ResponseEntity<?> deleteTmColorById(Long id);
}
