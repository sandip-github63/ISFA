package com.isfa.clientadminpanel.leave.response;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.isfa.clientadminpanel.leave.entities.Icon;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonInclude(Include.NON_NULL)
@EqualsAndHashCode(callSuper=false)
public class IconResponse extends BaseResponse<IconResponse>{

	public static final Logger logger = LoggerFactory.getLogger(IconResponse.class);
	
	private Long iconId;
	private String iconType;
	private Boolean active;
	
	
	public static BaseResponse<IconResponse> convert(Icon icon) {
		logger.info("IconResponse method converting Icon obj to IconResponse obj executing");
		IconResponse response = new IconResponse();
		BaseResponse<IconResponse> bResp = new BaseResponse<>();
		response.setIconId(icon.getIconId());
		response.setIconType(icon.getIconType());
		response.setActive(icon.getActive());
		bResp.setData(response);
		logger.info("IconResponse method converting Icon obj to IconResponse obj completed");
		return bResp;
	}

	
	public static BaseResponse<IconResponse> convertList(List<Icon> iconList) {
		logger.info("IconResponse method converting Icon List type obj to IconResponse List type obj executing");
		List<IconResponse> list = new ArrayList<>(iconList.size());
		BaseResponse<IconResponse> bResp = new BaseResponse<>();
		if(iconList.isEmpty()) {
			logger.info("IconResponse method converting Icon List type obj to IconResponse List type obj completed");
			bResp.setDataList(list);
			return bResp;
		}
		
		for(Icon icon:iconList) {
			IconResponse response = new IconResponse();
			response.setIconId(icon.getIconId());
			response.setIconType(icon.getIconType());
			response.setActive(icon.getActive());
			//response.setMessage("Icon at "+icon.getIconId()+" successfully fetched");
			
			list.add(response);
		}
		logger.info("IconResponse method converting Icon List type obj to IconResponse List type obj completed");
		bResp.setDataList(list);
		return bResp;
	}
	
}
