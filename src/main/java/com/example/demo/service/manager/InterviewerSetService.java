package com.example.demo.service.manager;

import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Interviewer;
import com.example.demo.model.entity.Manager;
import com.example.demo.repository.InterviewersRepository;
import com.example.demo.repository.ManagersRepository;

import lombok.RequiredArgsConstructor;

/*
 * 実施者情報更新処理群
 * 
 * @author ys
 * 
 */

@Service("managerInterviewerSetService")
@RequiredArgsConstructor

public class InterviewerSetService {

	private final PasswordEncoder passEncoder;
	
	private final InterviewersRepository repository;
	
	private final ManagersRepository managersRepository;
	
	
	/*
	 * idから実施者情報絞り込み処理
	 * 
	 * @param id 選択された実施者id
	 * @return 実施者エンティティ1件
	 * 
	 */
	
	public Interviewer getInterviewer(Integer id) {
		
		Interviewer interviewerInfo = repository.findById(id).orElse(null);
		
		return interviewerInfo;
		
	}
	
	
	/*
	 * idと更新情報から実施者情報更新処理
	 * 
	 * @param id 選択された実施者id
	 * @param updates 更新対象の情報群
	 * @param managerId 更新を担当する管理者id
	 * @return 実施者エンティティ1件
	 * 
	 */
	
	public Interviewer setUser(Integer id, Map<String, String> updates, Integer managerId) {
		
		Interviewer interviewerInfo = repository.findById(id).orElse(null);
		
		updates.forEach( (key, value) -> {
			
			switch (key) {
			
				case "name":
					
					interviewerInfo.setName(value);
					break;
					
				case "kana":
					
					interviewerInfo.setKana(value);
					break;
					
				case "email":
					
					interviewerInfo.setEmail(value);
					break;
					
				case "pass":
					
					String afterPass = passEncoder.encode(value);
					interviewerInfo.setPass(afterPass);
					break;
					
			}
			
		});
		
		Manager managerInfo = managersRepository.findById(managerId).orElse(null);
		
		interviewerInfo.setManager(managerInfo);
		
		return repository.save(interviewerInfo);
		
	}
	
}
