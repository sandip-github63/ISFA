package com.isfa.promoter.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isfa.promoter.entities.StoreDetail;

@Repository
public interface StoreDetailRepository extends JpaRepository<StoreDetail, Long>{

	Optional<StoreDetail> findByStoreId(Long storeId);
	
}

