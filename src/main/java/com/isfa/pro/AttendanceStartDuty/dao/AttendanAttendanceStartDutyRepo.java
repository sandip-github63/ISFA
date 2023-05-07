package com.isfa.pro.AttendanceStartDuty.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isfa.pro.Attendance.entity.AttendanceEntity;
import com.isfa.pro.AttendanceStartDuty.entity.AttendanceStartDutyEntity;

@Repository
public interface AttendanAttendanceStartDutyRepo extends JpaRepository<AttendanceStartDutyEntity, Long> {
   public AttendanceStartDutyEntity findByUserIdAndStoreId(String userId,int storeId);
   
}
