package com.isfa.clientadminpanel.promoter.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.isfa.clientadminpanel.promoter.dao.CategoryRepository;
import com.isfa.clientadminpanel.promoter.dao.ProductRepository;
import com.isfa.clientadminpanel.promoter.dao.StoreUserAssignmentRepository;
import com.isfa.clientadminpanel.promoter.entities.Category;
import com.isfa.clientadminpanel.promoter.entities.Product;
import com.isfa.clientadminpanel.promoter.entities.StoreUserAssignment;
import com.isfa.clientadminpanel.promoter.response.ProductDetailResponse;
import com.isfa.clientadminpanel.promoter.response.ProductResponse;
import com.isfa.promoter.dao.StockAssignmentRepository;
import com.isfa.promoter.dao.StockBalanceRepository;
import com.isfa.promoter.dao.StoreProductMappingRepository;
import com.isfa.promoter.entities.StockAssignment;
import com.isfa.promoter.entities.StockBalance;
import com.isfa.promoter.entities.StoreProductMapping;
import com.isfa.promoter.response.BaseResponse;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

	@Autowired
	StoreUserAssignmentRepository userAssignmentRepository;

	@Autowired
	StoreProductMappingRepository mappingRepository;

	@Autowired
	StockAssignmentRepository assignmentRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	StockBalanceRepository balanceRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	private Long noOfProducts=0L;
	
	@Override
	public ResponseEntity<?> getInventory(Long userId, Long companyId, Long storeId) {

		BaseResponse<ProductDetailResponse> bResp = new BaseResponse<>();
		ProductDetailResponse response = new ProductDetailResponse();
		StoreUserAssignment assignment = userAssignmentRepository.findByUserIdAndCampaignIdAndStoreId(userId, companyId, storeId).orElse(null);
		List<ProductResponse> prodRespList = new ArrayList<>();

		if (assignment != null) {
			List<StoreProductMapping> mapp = mappingRepository.findByStoreId(storeId);
			
			if (!mapp.isEmpty()) {
//				response.setNumberOfProduct(Long.valueOf(mapp.size()));
				
				for(StoreProductMapping productMapping : mapp) {
					
					Product product = productRepository.findById(productMapping.getProductId()).orElse(null);
					
					if(product!=null) {
						ProductResponse prodResp = new ProductResponse();
						prodResp.setProductId(product.getProductId());
						prodResp.setCategoryId(product.getCategoryId());
						prodResp.setProductName(product.getProductName()+"-"+product.getProductCode());
						prodResp.setPrice(product.getPrice());
						
						StockBalance balance = balanceRepository.findByProductIdAndStoreId(product.getProductId(), storeId).orElse(null);
						if(balance!=null) {
							prodResp.setStockBalance(balance.getBalance());
							noOfProducts+=balance.getBalance();
						}
						
						Category category = categoryRepository.findById(product.getCategoryId()).orElse(null);
						if(category!=null) {
							prodResp.setCategoryName(category.getCategoryName());
						}
						prodRespList.add(prodResp);
					}
				}
				response.setProductList(prodRespList);
				response.setNumberOfProduct(noOfProducts);
			}

			response.setNumberOfSelling(assignmentRepository.countByTransTypeAndTransactionType("DR", "SALE"));
			
			StockAssignment lastSale = assignmentRepository.findLastSale().orElse(null);
			
			if (lastSale.getCreatedDate() != null) {
				response.setLastReciveDate(lastSale.getCreatedDate().toLocalDate().toString());
			}
			
			
			// assuming openingBalance and closingBalance are Double type
			Double openingBalance = assignmentRepository.getTotalPriceForCRReceive(storeId);
			Double closingBalance = assignmentRepository.getTotalPriceForDRSale(storeId);

			// create a NumberFormat instance with Locale.INDIA for Indian currency format
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

			// format the opening and closing balance using the currencyFormat
			String formattedOpeningBalance = currencyFormat.format(openingBalance);
			String formattedClosingBalance = currencyFormat.format(closingBalance);
			
			
			response.setOpeningBalance(formattedOpeningBalance);
			response.setClosingBalance(formattedClosingBalance);
			
			bResp.setData(response);
			bResp.setStatus("200");
			bResp.setMessage("Inventory List successfully found for provided details");

		} else {
			bResp.setStatus("200");
			bResp.setMessage("No store found for provided details");
		}

		return new ResponseEntity<>(bResp, HttpStatus.OK);
	}

}
