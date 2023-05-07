package com.isfa.clientadminpanel.leave.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isfa.clientadminpanel.leave.entities.Color;

public interface ColorRepository extends JpaRepository<Color, Long> {
	
	public Optional<Color> findById(Long id);
}
