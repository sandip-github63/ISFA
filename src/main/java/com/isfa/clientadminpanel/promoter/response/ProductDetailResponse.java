package com.isfa.clientadminpanel.promoter.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetailResponse {

	private Long numberOfProduct;
	private Long numberOfSelling;
	private String lastReciveDate;
	private String openingBalance;
	private String closingBalance;
	
	List<ProductResponse> productList;
	
}
