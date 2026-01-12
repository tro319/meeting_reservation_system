package com.example.demo.service.manager;

import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Interviewer;
import com.example.demo.repository.InterviewersRepository;

import lombok.RequiredArgsConstructor;

/*
 * 実施者情報取得処理群
 * 
 * @author ys
 * 
 */

@Service("managerInterviewerGetService")
@RequiredArgsConstructor

public class InterviewerGetService {
	
	private final InterviewersRepository repository;
	
	
	/*
	 * idから実施者情報絞り込み処理
	 * 
	 * @param id 選択された実施者id
	 * @return 実施者エンティティ1件
	 * 
	 */
	
	public Interviewer  getInterviewer(Integer id) {
		
		Interviewer interviewerInfo = repository.findById(id).orElse(null);
		
		return interviewerInfo;
		
	}

}
