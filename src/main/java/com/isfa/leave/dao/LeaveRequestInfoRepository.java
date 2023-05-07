package com.isfa.leave.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isfa.leave.entities.LeaveRequestInfo;

public interface LeaveRequestInfoRepository extends JpaRepository<LeaveRequestInfo, Long> {
	
//	List<LeaveRequestInfo> findAllByUserIdAndCompanyId(Long userId, Long companyId);
	
	List<LeaveRequestInfo> findAllByUserIdAndCompanyIdOrderByModifiedDateDesc(Long userId, Long companyId);


}
