package com.example.demo.service.manager;

import org.dozer.DozerBeanMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Interviewer;
import com.example.demo.model.entity.Manager;
import com.example.demo.model.form.manager.SignupForm;
import com.example.demo.repository.InterviewersRepository;
import com.example.demo.repository.ManagersRepository;

import lombok.RequiredArgsConstructor;

/* 
 * 実施者登録処理群
 * 
 * @author ys
 * 
 */

@Service("managerInterviewerSignupService")
@RequiredArgsConstructor

public class InterviewerSignupService {

	
	private final InterviewersRepository repository;
	
	private final ManagersRepository managersRepository;
	
	private final PasswordEncoder passEncoder;
	
	private final DozerBeanMapper mapper;
	
	
	/*
	 * 実施者登録処理
	 * 
	 * @param form 実施者登録用フォーム
	 * @param managerId 登録を担当する管理者id
	 * @return 実施者エンティティ1件
	 * 
	 */
	
	public Interviewer register(SignupForm form, Integer managerId) {
		
		Interviewer interviewerInfo = mapper.map(form, Interviewer.class);
		
		String encodedPass = passEncoder.encode(form.getPass());
		
		interviewerInfo.setPass(encodedPass);
		
		Manager managerInfo = managersRepository.findById(managerId).orElse(null);
		
		interviewerInfo.setManager(managerInfo);
		
		return repository.save(interviewerInfo);
		
	}
	
	
	/*
	 * 実施者情報重複チェック処理
	 * 
	 * @param form 実施者登録用フォーム
	 * @return 重複があったか T/F
	 * 
	 */
	
	
	public Boolean checkDouble(SignupForm form) {
		
		String name = form.getName();
		
		String email = form.getEmail();
		
		Boolean nameCheck = repository.existsByName(name);
		
		Boolean emailCheck = repository.existsByEmail(email);
		
		return nameCheck || emailCheck;
		
	}
	
	
}
