package com.isfa.clientadminpanel.leave.response;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.isfa.clientadminpanel.leave.entities.Color;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(Include.NON_NULL)
public class ColorResponse extends BaseResponse<ColorResponse>{

	public static final Logger logger = LoggerFactory.getLogger(ColorResponse.class);
	
	private Long colorId;
	private String type;
	private Boolean active;
	
	
	public static BaseResponse<ColorResponse> convert(Color color) {
		logger.info("ColorResponse method converting Color obj to ColorResponse obj executing");
		ColorResponse response = new ColorResponse();
		BaseResponse<ColorResponse> bResp = new BaseResponse<>();
		
		response.setColorId(color.getId());
		response.setType(color.getType());
		response.setActive(color.getActive());
		bResp.setData(response);
		logger.info("ColorResponse method converting Color obj to ColorResponse obj completed");
		return bResp;
	}
	
	public static BaseResponse<ColorResponse> convertList(List<Color> colorList){
		logger.info("ColorResponse method converting Color List type obj to ColorResponse List type obj executing");
		List<ColorResponse> list = new ArrayList<>(colorList.size());
		BaseResponse<ColorResponse> bResp = new BaseResponse<>();
		if(colorList.isEmpty()) {
			logger.info("ColorResponse method converting Color List type obj to ColorResponse List type obj completed");
			bResp.setDataList(list);
			return bResp;
		}
		
		for(Color color:colorList) {
			
			ColorResponse response = new ColorResponse();
			
			response.setColorId(color.getId());
			response.setType(color.getType());
			response.setActive(color.getActive());
		//	response.setMessage("Color at "+response.getColorId()+" successfully fetched");
		
			list.add(response);
		}
		bResp.setDataList(list);
		logger.info("ColorResponse method converting Color List type obj to ColorResponse List type obj completed");
		return bResp;
	}
}
