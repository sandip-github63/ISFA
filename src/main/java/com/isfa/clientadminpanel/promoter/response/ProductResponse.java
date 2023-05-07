package com.isfa.clientadminpanel.promoter.response;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isfa.clientadminpanel.promoter.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

	public static final Logger logger = LoggerFactory.getLogger(ProductResponse.class);
	
	private Long productId;
	private Long categoryId;
	private String categoryName; 
	private String productName;
//	private String productCode;
    private Double price;
    private Long stockBalance;
    
//    private String productDescription;

    
    public static BaseResponse<ProductResponse> convert(Product product){
    	logger.info("ProductResponse method converting Product obj to ProductResponse obj executing");
    	BaseResponse<ProductResponse> bResp = new BaseResponse<>();
    	ProductResponse resp = new ProductResponse();
    	
    	resp.setProductName(product.getProductName()+"-"+product.getProductCode());
    	resp.setPrice(product.getPrice());
    	resp.setProductId(product.getProductId());
    	resp.setCategoryId(product.getCategoryId());
//    	resp.setStockBalance(product.get);
    	
    	bResp.setData(resp);
    	logger.info("ProductResponse method converting Product obj to ProductResponse obj completed");
    	return bResp;
    }
    
    public static BaseResponse<ProductResponse> convertList(List<Product> list){
    	logger.info("ProductResponse method converting Product List type obj to ProductResponse List type obj executing");
    	BaseResponse<ProductResponse> bResp = new BaseResponse<>();
    	List<ProductResponse> respList = new ArrayList<>();
    	
    	if(list.isEmpty()) {
    		logger.info("ProductResponse method converting Product List type obj to ProductResponse List type obj completed");
    		bResp.setDataList(respList);
    		return bResp;
    	}
    	
    	for(Product product : list) {
    		ProductResponse response = new ProductResponse();
    		
    		response.setProductName(product.getProductName()+"-"+product.getProductCode());
    		response.setPrice(product.getPrice());
    		response.setProductId(product.getProductId());
    		response.setCategoryId(product.getCategoryId());
    		
    		respList.add(response);
    	}
    	bResp.setDataList(respList);
    	logger.info("ProductResponse method converting Product List type obj to ProductResponse List type obj completed");
    	return bResp;
    }
	
    
    
}
