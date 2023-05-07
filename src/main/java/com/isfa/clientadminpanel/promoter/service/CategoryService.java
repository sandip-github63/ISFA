package com.isfa.clientadminpanel.promoter.service;

import org.springframework.http.ResponseEntity;

import com.isfa.clientadminpanel.promoter.model.CategoryRequest;
import com.isfa.clientadminpanel.promoter.response.BaseResponse;
import com.isfa.clientadminpanel.promoter.response.CategoryResponse;

public interface CategoryService {

    ResponseEntity<?> getAllCategories();

    ResponseEntity<?> getCategoryById(Long userId, Long companyId, Long storeId);

    BaseResponse<CategoryResponse> createCategory(CategoryRequest category);

    ResponseEntity<?> updateCategory(Long categoryId, CategoryRequest category);

    ResponseEntity<?> deleteCategory(Long categoryId);
}
