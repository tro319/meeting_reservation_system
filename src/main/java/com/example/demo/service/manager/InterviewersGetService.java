package com.example.demo.service.manager;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Interviewer;
import com.example.demo.repository.InterviewersRepository;

import lombok.RequiredArgsConstructor;


/*
 * 実施者一覧取得処理サービス群
 * 
 * @author ys
 * 
 */

@Service("managerInterviewersGetService")
@RequiredArgsConstructor

public class InterviewersGetService {
	
	
	private final InterviewersRepository repository;
	
	/* 実施者一覧取得処理
	 * 
	 * 
	 * @return 取得した実施者リスト
	 * 
	 */
	
	public List<Interviewer> getInterviewers() {
		
		List<Interviewer> interviewerInfos = repository.findAll();
		
		return interviewerInfos;
		
	}
	

}
