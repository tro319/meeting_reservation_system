package com.example.demo.service.manager;

import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Manager;
import com.example.demo.repository.ManagersRepository;

import lombok.RequiredArgsConstructor;

/*
 * 管理者ログインサービス群
 * 
 * @author ys
 * 
 */

@Service
@RequiredArgsConstructor

public class ManagerLoginService {

	private final ManagersRepository repository;
	
	
	/*
	 * emailカラムから管理者情報絞り込み
	 * 
	 * @param email 入力されたメールアドレス
	 * @return 管理者エンティティ1件
	 * 
	 */
	
	public Manager getManager(String email) {
		
		Manager managerInfo = repository.findByEmail(email).orElse(null);
		
		
		return managerInfo;
		
	}
	
}
