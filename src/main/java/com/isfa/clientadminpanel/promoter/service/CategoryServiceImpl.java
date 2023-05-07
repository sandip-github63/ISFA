package com.isfa.clientadminpanel.promoter.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.isfa.clientadminpanel.promoter.model.CategoryRequest;
import com.isfa.clientadminpanel.promoter.response.BaseResponse;
import com.isfa.clientadminpanel.promoter.response.CategoryResponse;
import com.isfa.clientadminpanel.promoter.response.ProductResponse;
import com.isfa.promoter.dao.StockBalanceRepository;
import com.isfa.promoter.dao.StoreProductMappingRepository;
import com.isfa.promoter.entities.StockBalance;
import com.isfa.promoter.entities.StoreProductMapping;

@Service
public class CategoryServiceImpl implements CategoryService {

	public static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	CategoryRepository repository;

	@Autowired
	StoreProductMappingRepository mappingRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	StoreUserAssignmentRepository userAssignmentRepository;

	@Autowired
	StockBalanceRepository stockBalanceRepository;

	@Override
	public ResponseEntity<?> getAllCategories() {
		logger.info("Category Service getting All Category executing");
		logger.info("Category Repository getting All Category executing");
		List<Category> list = repository.findAll();
		logger.info("Category Repository getting All Category completed");
		BaseResponse<CategoryResponse> response = CategoryResponse.convertList(list);

		if (response.getDataList().isEmpty()) {
			logger.info("Category List is empty");
			response.setMessage("No records to Fetch");
			response.setStatus("404");
			logger.info("Category Service Getting All Category completed");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		logger.info("Category List exists with some data");
		response.setMessage("Records successfully fetched");
		response.setStatus("200");
		logger.info("Category Service Getting All Category completed");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<?> getCategoryById(Long userId, Long companyId, Long storeId) {
		logger.info("Category Service Getting Category By Id executing");
		logger.info("Category Repository Getting Category By Id executing");

		StoreUserAssignment assign = userAssignmentRepository
				.findByUserIdAndCampaignIdAndStoreId(userId, companyId, storeId).orElse(null);

		if (assign == null) {
			logger.info("Category at provided id doesn't exist");
			BaseResponse<CategoryResponse> response = new BaseResponse<>();
			response.setMessage("No category is assigned");
			response.setStatus("200");
			logger.info("Category Service Getting Category By Id completed");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		List<StoreProductMapping> storeMappingList = mappingRepository.findByStoreId(storeId);
		Set<Long> categoryIds = new HashSet<>();

		if (!storeMappingList.isEmpty()) {
			for (StoreProductMapping mapp : storeMappingList) {
				Product existProduct = productRepository.findById(mapp.getProductId()).orElse(null);
				if (existProduct != null) {
					categoryIds.add(existProduct.getCategoryId());
				}
			}
		}

		List<Category> categories = repository.findDistinctByCategoryIdIn(new ArrayList<>(categoryIds));
		logger.info("Category Repository Getting Category By Id completed");

		if (!categories.isEmpty()) {
			List<CategoryResponse> categoryResponses = new ArrayList<>();
			for (Category category : categories) {
				CategoryResponse categoryResponse = new CategoryResponse(category.getCategoryId(),
						category.getCategoryName(), null);

				List<Product> products = productRepository.findByCategoryId(category.getCategoryId());
				List<ProductResponse> productResponses = new ArrayList<>();
				for (Product product : products) {

					StockBalance balance = stockBalanceRepository
							.findByProductIdAndStoreId(product.getProductId(), storeId).orElse(null);
					
					ProductResponse response =new  ProductResponse(product.getProductId(), product.getCategoryId(),null,
							product.getProductName()+"-"+product.getProductCode(), product.getPrice(),null);
					
					if (balance != null) {
						response.setStockBalance(balance.getBalance());
					}
					productResponses.add(response);
				}

				categoryResponse.setProductList(productResponses);
				categoryResponses.add(categoryResponse);
			}

			BaseResponse<List<CategoryResponse>> response = new BaseResponse<>();
			response.setDataListIs(categoryResponses);
			response.setMessage("Record successfully fetched");
			response.setStatus("200");
			logger.info("Category Service Getting Category By Id completed");
			return new ResponseEntity<>(response, HttpStatus.OK);

		} else {
			logger.info("Category at provided id doesn't exist");
			BaseResponse<CategoryResponse> response = new BaseResponse<>();
			response.setMessage("Record not exists to fetch");
			response.setStatus("200");
			logger.info("Category Service Getting Category By Id completed");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public BaseResponse<CategoryResponse> createCategory(CategoryRequest request) {
		logger.info("Category Service Saving New Category executing");
		LocalDateTime now = LocalDateTime.now();

		request.setCreatedDate(now);
		request.setModifiedDate(now);

		Category category = request.convertInto(request);
		logger.info("Category Repository Saving New Category executing");
		category = repository.save(category);
		BaseResponse<CategoryResponse> response = CategoryResponse.convert(category);
		logger.info("Category Repository Saving New Category completed");
		response.setMessage("Record successfully inserted");
		response.setStatus("200");
		logger.info("Category Service Saving New Category completed");
		return response;
	}

	@Override
	public ResponseEntity<?> updateCategory(Long categoryId, CategoryRequest request) {
		logger.info("Category Service Updating existing Category executing");
		logger.info("Category Repository Updating existing Category executing");
		Optional<Category> existingCateogory = repository.findById(categoryId);
		logger.info("Category Repository Updating existing Category completed");

		if (existingCateogory.isPresent()) {
			logger.info("Category exists at provided id...in order to update");
			request.setCategoryId(categoryId);
			BaseResponse<CategoryResponse> response = createCategory(request);

			response.setMessage("Category Successfully Updated");
			response.setStatus("200");
			logger.info("Category Service Updating existing Category completed");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		logger.info("Category do not exists at provided id...in order to update");
		BaseResponse<CategoryResponse> response = new BaseResponse<>();
		response.setMessage("Record not found to update");
		response.setStatus("404");
		logger.info("Category Service Updating Existing Category completed");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<?> deleteCategory(Long categoryId) {
		logger.info("Category Service Deleting Category By Id executing");
		logger.info("Category Repository Deleting Category By Id executing");
		Optional<Category> existingCateogory = repository.findById(categoryId);
		logger.info("Category Repository Deleting Category By Id completed");

		if (existingCateogory.isPresent()) {
			logger.info("Category exists at provided id...in order to delete");
			repository.deleteById(categoryId);

			BaseResponse<CategoryResponse> response = new BaseResponse<>();
			response.setMessage("Record successfully deleted");
			response.setStatus("200");
			logger.info("Category Service Deleting Existing Category completed");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		logger.info("Category do not exists at provided id...in order to delete");
		BaseResponse<CategoryResponse> response = new BaseResponse<>();
		response.setMessage("Record not found to delete");
		response.setStatus("404");
		logger.info("Category Service Deleting Existing Category completed");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

}
