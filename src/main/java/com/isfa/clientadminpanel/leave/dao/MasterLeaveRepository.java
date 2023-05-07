package com.isfa.clientadminpanel.leave.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isfa.clientadminpanel.leave.entities.MasterLeave;

@Repository
public interface MasterLeaveRepository extends JpaRepository<MasterLeave, Long> {
}
