package com.isfa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isfa.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
  
  User findByEmail(String email);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
  
  @Query("SELECT u FROM User u WHERE u.supervisor = :supervisor")
  List<User> findUsersSupervisedBy(@Param("supervisor") String supervisor);

  List<User> findByCompanyIdAndIdNot(Long companyId, Long userId);
  List<User> findByCompanyId(Long companyId);

}
