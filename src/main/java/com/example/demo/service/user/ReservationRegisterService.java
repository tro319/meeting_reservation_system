package com.example.demo.service.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/*
 * 予約登録処理サービス群
 * 
 * @author ys
 * 
 */

import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Interviewer;
import com.example.demo.model.entity.Reservation;
import com.example.demo.model.entity.Time;
import com.example.demo.model.entity.User;
import com.example.demo.model.form.user.ReservationRegisterForm;
import com.example.demo.repository.InterviewersRepository;
import com.example.demo.repository.ReservationsRepository;
import com.example.demo.repository.TimesRepository;
import com.example.demo.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service("userReservationRegisterService")
@RequiredArgsConstructor

public class ReservationRegisterService {
	
	
	private final ReservationsRepository repository;
	
	private final TimesRepository timesRepository;
	
	private final UsersRepository usersRepository;
	
	private final InterviewersRepository interviewersRepository;
	
	/* 予約登録処理
	 * 
	 * 
	 * @param form 予約登録フォーム入力情報
	 * @return 登録した予約エンティティ 1件
	 * 
	 */
	
	public Reservation registerReservation(ReservationRegisterForm form) {
		
		Reservation reservationInfo = new Reservation();
		
		System.out.println(form.getDate());
		
		LocalDate date = LocalDate.parse(form.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		Time timeInfo = timesRepository.findById(form.getTimeId()).orElse(null);
		
		User userInfo = usersRepository.findById(form.getUserId()).orElse(null);
		
		Interviewer interviewerInfo = interviewersRepository.findById(form.getInterviewerId()).orElse(null);
		
		reservationInfo.setDate(date);
		
		reservationInfo.setTime(timeInfo);
		
		reservationInfo.setUser(userInfo);
		
		reservationInfo.setInterviewer(interviewerInfo);
		
		return repository.save(reservationInfo);
		
	}
	
	/* 予約登録情報重複チェック処理
	 * 
	 * 
	 * @param form 予約登録フォーム入力情報
	 * @return 既に存在したかのT/F
	 * 
	 */
	
	public Boolean doubleCheck(ReservationRegisterForm form) {
		
		Boolean doubleCheck = true; 
		
		List<Reservation> reservationInfos = repository.findAll();
		
		doubleCheck = reservationInfos.stream().anyMatch(reservation -> (reservation.getDate().equals(LocalDate.parse(form.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))) && (reservation.getTime().getId() == form.getTimeId()) && (reservation.getUser().getId() == form.getUserId()));
		
		
		return doubleCheck;
		
		
	}
	

}
