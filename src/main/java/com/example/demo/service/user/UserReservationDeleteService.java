package com.example.demo.service.user;

/*
 * 予約一覧取得処理サービス群
 * 
 * @author ys
 * 
 */

import org.springframework.stereotype.Service;

import com.example.demo.repository.ReservationsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UserReservationDeleteService {
	
	
	private final ReservationsRepository repository;
	
	/* 予約データ削除処理
	 * 
	 * 
	 * @param id 予約id
	 * 
	 */
	
	public void deleteReservation(Integer id) {
		
		repository.deleteById(id);
		
	}
	

}
