package com.example.demo.service.user;

import org.springframework.stereotype.Service;

import com.example.demo.model.entity.User;
import com.example.demo.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

/*
 * ユーザーログインサービス群
 * 
 * @author ys
 * 
 */

@Service
@RequiredArgsConstructor

public class LoginService {

	private final UsersRepository repository;
	
	
	/*
	 * emailカラムからユーザー情報絞り込み
	 * 
	 * @param email 入力されたメールアドレス
	 * @return ユーザーエンティティ1件
	 * 
	 */
	
	public User getUser(String email) {
		
		User userInfo = repository.findByEmail(email).orElse(null);
		
		
		return userInfo;
		
	}
	
}
