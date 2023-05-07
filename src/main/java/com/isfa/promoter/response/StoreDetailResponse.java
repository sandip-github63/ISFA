package com.isfa.promoter.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.isfa.promoter.entities.StoreDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class StoreDetailResponse {

	private Long clusterId;
	private Long storeId;
	private String storeCode;
	private String name;
    private String address;
    private String storeCategory;
    private String latitude;
    private String logitude;
    private String storeBranch;
    private String storeType;
    private Boolean activeStatus;
    private String phoneNo;
    private Long zipcode;
    private String contactName;

    static Logger logger = LoggerFactory.getLogger(StoreDetailResponse.class);
    
    public static BaseResponse<StoreDetailResponse> convert(StoreDetail detail){
    	logger.info("StoreDetailResponse convert() method executing");
    	BaseResponse<StoreDetailResponse> bResp = new BaseResponse<>();
    	StoreDetailResponse response = new StoreDetailResponse();

    	response.setClusterId(detail.getClusterId());
    	response.setStoreId(detail.getStoreId());
    	response.setStoreCode(detail.getStoreCode());
    	response.setName(detail.getStoreName());
    	response.setAddress(detail.getAddress());
    	response.setStoreCategory(detail.getStoreCategory());
    	response.setLatitude(detail.getLatitude());
    	response.setLogitude(detail.getLongitude());
    	response.setStoreBranch(detail.getStoreBranch());
    	response.setStoreType(detail.getStoreType());
    	response.setActiveStatus(detail.getActiveStatus());
    	response.setPhoneNo(detail.getPhoneNo());
    	response.setZipcode(detail.getZipcode());
    	response.setContactName(detail.getContactName());
    	
    	bResp.setData(response);
    	logger.info("StoreDetailResponse convert() method executing");
    	return bResp;
    }
    
}
