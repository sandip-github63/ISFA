package com.isfa.clientadminpanel.promoter.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isfa.clientadminpanel.promoter.entities.StoreUserAssignment;

@Repository
public interface StoreUserAssignmentRepository extends JpaRepository<StoreUserAssignment, Long> {

//	List<StoreUserAssignment> findByStoreId(Long storeId);

//	List<StoreUserAssignment> findByUserId(Long userId);

//	List<StoreUserAssignment> findByCampaignId(Long campaignId);

	Optional<StoreUserAssignment> findByUserIdAndCampaignIdAndStoreId(Long userId, Long campaignId, Long storeId);

	Optional<StoreUserAssignment> findByCampaignIdAndStoreId(Long campaignId, Long storeId);

	Optional<StoreUserAssignment> findByUserIdAndCampaignId(Long userId, Long campaignId);
}
