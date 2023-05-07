package com.isfa.promoter.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isfa.promoter.entities.StockBalance;

public interface StockBalanceRepository extends JpaRepository<StockBalance, Long> {

	Optional<StockBalance> findByProductIdAndStoreId(Long productId,Long storeId);
	
}
