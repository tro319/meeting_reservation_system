package com.example.demo.service.manager;

import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Manager;
import com.example.demo.repository.ManagersRepository;

import lombok.RequiredArgsConstructor;

/*
 * 管理者情報取得処理群
 * 
 * @author ys
 * 
 */

@Service
@RequiredArgsConstructor

public class ManagerGetService {
	
	private final ManagersRepository repository;
	
	
	/*
	 * idから管理者情報絞り込み処理
	 * 
	 * @param id ログイン中id
	 * @return 管理者エンティティ1件
	 * 
	 */
	
	public Manager getManager(Integer id) {
		
		Manager managerInfo = repository.findById(id).orElse(null);
		
		return managerInfo;
		
	}

}
