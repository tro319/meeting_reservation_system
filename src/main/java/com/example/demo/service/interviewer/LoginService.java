package com.example.demo.service.interviewer;

import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Interviewer;
import com.example.demo.repository.InterviewersRepository;

import lombok.RequiredArgsConstructor;

/*
 * 実施者ログインサービス群
 * 
 * @author ys
 * 
 */

@Service("interviewerLoginService")
@RequiredArgsConstructor

public class LoginService {

	private final InterviewersRepository repository;
	
	
	/*
	 * emailカラムから実施者情報絞り込み
	 * 
	 * @param email 入力されたメールアドレス
	 * @return 実施者エンティティ1件
	 * 
	 */
	
	public Interviewer getInterviewer(String email) {
		
		Interviewer interviewerInfo = repository.findByEmail(email).orElse(null);
		
		return interviewerInfo;
		
	}
	
}
