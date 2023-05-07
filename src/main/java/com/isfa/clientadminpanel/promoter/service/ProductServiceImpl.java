package com.isfa.clientadminpanel.promoter.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.isfa.clientadminpanel.promoter.dao.ProductRepository;
import com.isfa.clientadminpanel.promoter.entities.Product;
import com.isfa.clientadminpanel.promoter.model.ProductRequest;
import com.isfa.clientadminpanel.promoter.response.BaseResponse;
import com.isfa.clientadminpanel.promoter.response.ProductResponse;
import com.isfa.promoter.dao.StockBalanceRepository;
import com.isfa.promoter.dao.StoreProductMappingRepository;
import com.isfa.promoter.entities.StockBalance;
import com.isfa.promoter.entities.StoreProductMapping;


@Service
public class ProductServiceImpl implements ProductService{
	
	public static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StoreProductMappingRepository mappingRepository;
	
	@Autowired
	StockBalanceRepository stockBalanceRepository;

	
//	@Autowired
//	private UserRepository userRepository;
	
	@Override
	public ResponseEntity<?> getAllProducts(Long storeId) {
		logger.info("Product Service getting All Product executing");
    	logger.info("Product Repository getting All Product executing");
		List<StoreProductMapping> mapList = mappingRepository.findByStoreId(storeId);
		
		List<ProductResponse> productList = new ArrayList<>();
		
		if(!mapList.isEmpty()) {
			for(StoreProductMapping storeProduct : mapList) {
				Product product = productRepository.findById(storeProduct.getProductId()).orElse(null);
				if(product!=null) {
					
					
					StockBalance balance = stockBalanceRepository
							.findByProductIdAndStoreId(product.getProductId(), storeId).orElse(null);
					
					ProductResponse response =new  ProductResponse(product.getProductId(), product.getCategoryId(),null,
							product.getProductName()+"-"+product.getProductCode(),product.getPrice(),null);
					
					if (balance != null) {
						response.setStockBalance(balance.getBalance());
					}
					productList.add(response);
					
//					productList.add(product);
				}
			}
		}
		
		BaseResponse<ProductResponse> bResp = new BaseResponse<>();
		logger.info("Product Repository getting All Product completed");
//		if(bResp.getDataList().isEmpty()) {
//			logger.info("Product List is empty");
//			bResp.setMessage("No products found");
//			bResp.setStatus("404");
//			logger.info("Product Service Getting All Product completed");
//			return new ResponseEntity<>(bResp,HttpStatus.NOT_FOUND);
//		}
		logger.info("Product List exists with some data");
		bResp.setMessage("Products successfully fetched");
		bResp.setStatus("200");
		bResp.setDataList(productList);
		logger.info("Product Service Getting All Product completed");
		return new ResponseEntity<>(bResp,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> getProductById(Long productId) {
		logger.info("Product Service Getting Product By Id executing");
    	logger.info("Product Repository Getting Product By Id executing");
		Optional<Product> existProduct = productRepository.findById(productId);
		logger.info("Product Repository Getting Product By Id completed");
		if(existProduct.isPresent()) {
			logger.info("Product at provided id exists ");
			BaseResponse<ProductResponse> bResp = ProductResponse.convert(existProduct.get());
			
			bResp.setMessage("Product successfully fetched");
			bResp.setStatus("200");
			logger.info("Product Service Getting Product By Id completed");
			return new ResponseEntity<>(bResp,HttpStatus.OK);
		}
		logger.info("Product at provided id doesn't exists");
		BaseResponse<ProductResponse> bResp = new BaseResponse<>();
		
		bResp.setMessage("Product does not exists");
		bResp.setStatus("404");
		logger.info("Product Service Getting Product By Id completed");
		return new ResponseEntity<>(bResp,HttpStatus.NOT_FOUND);
	}
	
	
	@Override
	public BaseResponse<ProductResponse> createProduct(ProductRequest request) {
		logger.info("Product Service Saving New Product executing");
    	
		//setting created_by and modified_by
		
		LocalDateTime now = LocalDateTime.now();
		request.setCreatedDate(now);
		request.setModifiedDate(now);
		logger.info("Product Repository Saving New Product executing");
		Product product = productRepository.save(request.convertInto(request));
		logger.info("Product Repository Saving New Product completed");
		BaseResponse<ProductResponse> response = ProductResponse.convert(product);
		
		response.setMessage("Record successfully inserted");
		response.setStatus("200");
		logger.info("Product Service Saving New Product completed");
		return response;
	}
	
	@Override
	public ResponseEntity<?> updateProduct(Long productId, ProductRequest request) {
		logger.info("Product Service Updating existing Product executing");
    	logger.info("Product Repository Updating existing Product executing");
		Optional<Product> existProduct = productRepository.findById(productId);
		logger.info("Product Repository Updating existing Product completed");
		if(existProduct.isPresent()) {
			logger.info("Product exists at provided id...in order to update");
			request.setProductId(productId);
			BaseResponse<ProductResponse> bResp = createProduct(request);
			
			bResp.setMessage("Product successfully updated");
			bResp.setStatus("200");
			logger.info("Product Service Updating existing Product completed");
			return new ResponseEntity<>(bResp,HttpStatus.OK);
		}
		logger.info("Product do not exists at provided id...in order to update");
		BaseResponse<ProductResponse> bResp = new BaseResponse<>();
		
		bResp.setMessage("Product does not exists");
		bResp.setStatus("404");
		logger.info("Product Service Updating Existing Product completed");
		return new ResponseEntity<>(bResp,HttpStatus.NOT_FOUND);
	}
	
	
	@Override
	public ResponseEntity<?> deleteProduct(Long productId) {
		logger.info("Product Service Deleting Product By Id executing");
    	logger.info("Product Repository Deleting Product By Id executing");
		Optional<Product> existProduct = productRepository.findById(productId);
		logger.info("Product Repository Deleting Product By Id completed");
		BaseResponse<ProductResponse> bResp = new BaseResponse<>();
		
		if(existProduct.isPresent()) {
			logger.info("Product exists at provided id...in order to delete");
			productRepository.deleteById(productId);
			
			bResp.setMessage("Product successfully deleted");
			bResp.setStatus("200");
			logger.info("Product Service Deleting Existing Product completed");
			return new ResponseEntity<>(bResp,HttpStatus.OK);
		}
		logger.info("Product do not exists at provided id...in order to delete");
		bResp.setMessage("Product does not exists");
		bResp.setStatus("404");
		logger.info("Product Service Deleting Existing Product completed");
		return new ResponseEntity<>(bResp,HttpStatus.NOT_FOUND);
	}
}
