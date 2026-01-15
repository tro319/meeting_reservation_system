package com.example.demo.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Time;
import com.example.demo.repository.TimesRepository;

import lombok.RequiredArgsConstructor;

/*
 * 時間枠一覧取得サービス群
 * 
 * @author ys
 * 
 */

@Service("userTimesGetService")
@RequiredArgsConstructor

public class TimesGetService {
	
	private final TimesRepository repository;
	
	/*
	 * 時間枠一覧取得
	 * 
	 * @return 時間枠リスト
	 * 
	 */
	
	public List<Time> getTimes() {
		
		List<Time> timesInfo = repository.findAll();
		
		
		return timesInfo;
		
	}
	
}
