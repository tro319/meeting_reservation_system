package com.example.demo.service.user;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Reservation;
import com.example.demo.model.entity.Time;
import com.example.demo.repository.ReservationsRepository;
import com.example.demo.repository.TimesRepository;

import lombok.RequiredArgsConstructor;

/*
 * 予約状況取得処理サービス群
 * 
 * @author ys
 * 
 */

@Service("userReservationAblesGetService")
@RequiredArgsConstructor

public class ReservationAblesGetService {
	
	
	private final ReservationsRepository reservationsRepository;
	
	private final TimesRepository timesRepository;
	
	private final SevenDatesGetService datesGetService;
	
	/* 予約状況取得処理
	 * 
	 * 
	 * @param interviewerId 選択された実施者id
	 * @return 取得した予約状況真偽などのマップ
	 * 
	 */
	
	public Map<String, Map<Integer, Boolean>> getReservationAbles(Integer interviewerId) {
		
		List<LocalDate> dateInfos = datesGetService.getDates();
		
		List<Time> timeInfos = timesRepository.findAll();
		
		List<Reservation> reservationInfos = reservationsRepository.findByInterviewerId(interviewerId);
		
		
		// 判断用の情報を取得
		
		Set<String> reservedKeys = reservationInfos
										.stream()
											.map(reservation -> reservation.getDate() + "_" + reservation.getTime().getStart())
												.collect(Collectors.toSet());
		
		Map<String, Map<Integer, Boolean>> reservationAbleInfos = new LinkedHashMap<>();
		
		
		
		for (LocalDate date : dateInfos) {
			
			Map<Integer, Boolean> timeMap = new LinkedHashMap<>();
			
			Boolean isWeekend = date.getDayOfWeek().getValue() >= 6;
			
			for (Time time : timeInfos) {
				
				String key = date + "_" + time.getStart();
				
				Boolean isReserved = reservedKeys.contains(key);
				
				Boolean able = !isReserved && !isWeekend;
				
				timeMap.put(time.getStart(), able);
				
			}
			
			reservationAbleInfos.put(date.toString(), timeMap);
			
		}
		
		return reservationAbleInfos;
		
	}
	

}
