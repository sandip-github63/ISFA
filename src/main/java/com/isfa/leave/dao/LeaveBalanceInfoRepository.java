package com.isfa.leave.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isfa.leave.entities.LeaveBalanceInfo;

public interface LeaveBalanceInfoRepository extends JpaRepository<LeaveBalanceInfo, Long> {

	List<LeaveBalanceInfo> findAllByUserIdAndCompanyId(Long userId, Long companyId);

	Optional<LeaveBalanceInfo> findByUserIdAndCompanyIdAndLeaveId(Long userId, Long companyId, Long leaveId);
}
 
