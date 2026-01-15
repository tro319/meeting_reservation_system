package com.example.demo.service.user;

/*
 * 予約取得処理サービス群
 * 
 * @author ys
 * 
 */

import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Reservation;
import com.example.demo.repository.ReservationsRepository;

import lombok.RequiredArgsConstructor;

@Service("userReservationGetService")
@RequiredArgsConstructor

public class ReservationGetService {
	
	
	private final ReservationsRepository repository;
	
	/* 予約取得処理
	 * 
	 * 
	 * @param id 予約id
	 * @return 取得した予約エンティティ 1件
	 * 
	 */
	
	public Reservation getReservation(Integer id) {
		
		Reservation reservationInfo = repository.findById(id).orElse(null);
		
		return reservationInfo;
		
	}
	

}
