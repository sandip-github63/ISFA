package com.isfa.pro.Attendance.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isfa.pro.Attendance.entity.AttendanceEntity;

@Repository
public interface AttendanceRepo extends JpaRepository<AttendanceEntity, Long> {
   public AttendanceEntity findByUserIdAndStoreId(String userId,int storeId);
   
}
