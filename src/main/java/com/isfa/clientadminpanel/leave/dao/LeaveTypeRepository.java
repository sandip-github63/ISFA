package com.isfa.clientadminpanel.leave.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isfa.clientadminpanel.leave.entities.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {

}
