package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Reservation;

@Repository

public interface ReservationsRepository extends JpaRepository<Reservation, Integer> {
	
	
	List<Reservation> findByUserId(Integer userId);
	
	List<Reservation> findByInterviewerId(Integer interviewerId);
	
	List<Reservation> findByInterviewerName(String interviewerName);

	

}

