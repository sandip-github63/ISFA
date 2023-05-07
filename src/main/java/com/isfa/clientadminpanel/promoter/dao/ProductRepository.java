package com.isfa.clientadminpanel.promoter.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isfa.clientadminpanel.promoter.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByCategoryId(Long categpryId);
}
