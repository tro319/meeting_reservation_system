package com.example.demo.service.user;

import org.springframework.stereotype.Service;

import com.example.demo.model.entity.User;
import com.example.demo.repository.user.UsersRepository;

import lombok.RequiredArgsConstructor;

/*
 * ユーザー情報取得処理群
 * 
 * @author ys
 * 
 */

@Service
@RequiredArgsConstructor

public class UserGetService {
	
	private final UsersRepository repository;
	
	
	/*
	 * idからユーザー情報絞り込み処理
	 * 
	 * @param id ログイン中id
	 * @return ユーザーエンティティ1件
	 * 
	 */
	
	public User getUser(Integer id) {
		
		User userInfo = repository.findById(id).orElse(null);
		
		return userInfo;
		
	}

}
