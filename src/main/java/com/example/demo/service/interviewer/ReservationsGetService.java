package com.example.demo.service.interviewer;

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

@Service("interviewerReservationsGetService")
@RequiredArgsConstructor

public class ReservationsGetService {
	
	
	private final ReservationsRepository repository;
	
	/* 実施者が担当の予約一覧取得処理
	 * 
	 * 
	 * @param interviewerId ログイン中の実施者id
	 * @return 取得した予約リスト
	 * 
	 */
	
	public List<Reservation> getReservations(Integer interviewerId) {
		
		List<Reservation> reservationInfos = repository.findByInterviewerId(interviewerId);
		
		return reservationInfos;
		
	}
	

}
