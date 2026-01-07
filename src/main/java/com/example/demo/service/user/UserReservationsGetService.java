package com.example.demo.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Reservation;
import com.example.demo.repository.ReservationsRepository;

import lombok.RequiredArgsConstructor;

/*
 * 予約一覧取得処理サービス群
 * 
 * @author ys
 * 
 */

@Service
@RequiredArgsConstructor

public class UserReservationsGetService {
	
	
	private final ReservationsRepository repository;
	
	/* ユーザーの予約一覧取得処理
	 * 
	 * 
	 * @param userId ログイン中のユーザーid
	 * @return 取得した予約リスト
	 * 
	 */
	
	public List<Reservation> getReservations(Integer userId) {
		
		List<Reservation> reservationInfos = repository.findByUserId(userId);
		
		return reservationInfos;
		
	}
	

}
