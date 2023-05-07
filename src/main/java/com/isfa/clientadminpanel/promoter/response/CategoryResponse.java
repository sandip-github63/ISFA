package com.isfa.clientadminpanel.promoter.response;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isfa.clientadminpanel.promoter.entities.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CategoryResponse extends BaseResponse<CategoryResponse>{
	
	public static final Logger logger = LoggerFactory.getLogger(CategoryResponse.class);
	
	 private Long categoryId;

	 private String categoryName;
	 
	 private List<ProductResponse> productList;
	
	 public static BaseResponse<CategoryResponse> convert(Category category){
		 logger.info("CategoryResponse method converting Category obj to CategoryResponse obj executing");
		 BaseResponse<CategoryResponse> bResp = new BaseResponse<>();
		 CategoryResponse resp = new CategoryResponse();
		 
		 resp.setCategoryId(category.getCategoryId());
		 resp.setCategoryName(category.getCategoryName());
		 
		 bResp.setData(resp);
		 logger.info("CategoryResponse method converting Category obj to CategoryResponse obj completed");
		 return bResp;
	 }
	 
	 
	 public static BaseResponse<CategoryResponse> convertList(List<Category> list) {
		 logger.info("CategoryResponse method converting Category List type obj to CategoryResponse List type obj executing");
		 BaseResponse<CategoryResponse> bResp = new BaseResponse<>();
		 List<CategoryResponse> respList = new ArrayList<>();
		
		 if(list.isEmpty()) {
			 logger.info("CategoryResponse method converting Category List type obj to CategoryResponse List type obj completed");
			 bResp.setDataList(respList);
			 return bResp;
		 }
		 
		 for(Category category:list) {
			 CategoryResponse response = new CategoryResponse();
			 
			 response.setCategoryId(category.getCategoryId());
			 response.setCategoryName(category.getCategoryName());
			 
			 respList.add(response);
		 }
		 bResp.setDataList(respList);
		 logger.info("CategoryResponse method converting Category List type obj to CategoryResponse List type obj completed");
		 return bResp;
	}
}
