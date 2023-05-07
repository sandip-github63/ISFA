package com.isfa.clientadminpanel.leave.service;


import org.springframework.http.ResponseEntity;

import com.isfa.clientadminpanel.leave.model.IconRequest;
import com.isfa.clientadminpanel.leave.response.BaseResponse;
import com.isfa.clientadminpanel.leave.response.IconResponse;

public interface IconService {
	
	public ResponseEntity<?> getAllIcons();
    public ResponseEntity<?> getIconById(Long iconId);
    public BaseResponse<IconResponse> createIcon(IconRequest iconRequest);
    public ResponseEntity<?> updateIcon(Long iconId, IconRequest iconRequest);
    public ResponseEntity<?> deleteIcon(Long iconId);	
	
}
