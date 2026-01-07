package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Interviewer;

@Repository

public interface InterviewersRepository extends JpaRepository<Interviewer, Integer> {
	
	Optional<Interviewer> findByEmail(String email);

	Boolean existsByName(String name);
	
	Boolean existsByEmail(String email);

}

