package com.isfa.promoter.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isfa.promoter.entities.StoreProductMapping;

public interface StoreProductMappingRepository extends JpaRepository<StoreProductMapping, Long> {
	
	 List<StoreProductMapping> findByStoreId(Long storeId);
	
}
