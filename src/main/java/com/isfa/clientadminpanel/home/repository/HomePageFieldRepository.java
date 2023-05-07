package com.isfa.clientadminpanel.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isfa.clientadminpanel.home.entity.HomePageField;

public interface HomePageFieldRepository extends JpaRepository<HomePageField, Long> {
    // define custom query methods here if needed
}
