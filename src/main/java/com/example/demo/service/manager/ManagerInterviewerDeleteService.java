package com.example.demo.service.manager;

import org.springframework.stereotype.Service;

import com.example.demo.repository.InterviewersRepository;

import lombok.RequiredArgsConstructor;

/*
 * 実施者削除処理群
 * 
 * @author ys
 * 
 */

@Service
@RequiredArgsConstructor

public class ManagerInterviewerDeleteService {

	private final InterviewersRepository repository;
	
	
	/*
	 * 実施者削除処理
	 * 
	 * @param id 選択された実施者id
	 * 
	 */
	
	public void deleteInterviewer(Integer id) {
		
		repository.deleteById(id);
		
	}
	
}
