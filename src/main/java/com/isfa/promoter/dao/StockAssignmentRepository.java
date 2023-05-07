package com.isfa.promoter.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isfa.promoter.entities.StockAssignment;

@Repository
public interface StockAssignmentRepository extends JpaRepository<StockAssignment, Long> {

    // Add any custom repository methods here
	Optional<StockAssignment> findByProductIdAndStoreId(Long productId,Long storeId);
	
	Long countByTransTypeAndTransactionType(String transType, String transactionType);
	
	@Query("SELECT sa FROM StockAssignment sa WHERE sa.transType = 'CR' AND sa.transactionType = 'RECEIVE' AND sa.createdDate = (SELECT MAX(s.createdDate) FROM StockAssignment s WHERE s.transType = 'CR' AND s.transactionType = 'RECEIVE')")
	Optional<StockAssignment> findLastSale();
	
	@Query("SELECT SUM(sa.transUnit * sa.price) FROM StockAssignment sa WHERE sa.transType = 'CR' AND sa.transactionType = 'RECEIVE'AND sa.storeId = :storeId")
	Double getTotalPriceForCRReceive(@Param("storeId") Long storeId);
	
	@Query("SELECT SUM(sa.transUnit * sa.price) FROM StockAssignment sa WHERE sa.transType = 'DR' AND sa.transactionType = 'SALE' AND sa.storeId = :storeId")
	Double getTotalPriceForDRSale(@Param("storeId") Long storeId);
}
