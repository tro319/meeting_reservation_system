package com.example.demo.service.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/*
 * 現在日付から7日分取得サービス群
 * 
 * @author ys
 * 
 */

@Service("userSevenDatesGetService")
@RequiredArgsConstructor

public class SevenDatesGetService {
	
	
	/*
	 * 今日の日付を取得して、それから7日分取得
	 * 
	 * @return 日付リスト
	 * 
	 */
	
	public List<LocalDate> getDates() {
		
		LocalDate curDate = LocalDate.now();
		
		List<LocalDate> datesInfo = new ArrayList<>();
		
		
		for (int i = 0; i < 7; i++) {
			
			LocalDate nextDate = curDate.plusDays(i);
			
			datesInfo.add(nextDate);
			
		}
		
		
		return datesInfo;
		
	}
	
}
