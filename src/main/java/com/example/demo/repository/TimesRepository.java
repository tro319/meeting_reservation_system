package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Time;

@Repository

public interface TimesRepository extends JpaRepository<Time, Integer> {
	
	
	

	

}

