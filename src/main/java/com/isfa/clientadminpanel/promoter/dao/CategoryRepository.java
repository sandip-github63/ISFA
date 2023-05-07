package com.isfa.clientadminpanel.promoter.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isfa.clientadminpanel.promoter.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	List<Category> findDistinctByCategoryIdIn(List<Long> categoryIds);
	
}
