package com.example.demo.service.users;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dao.users.UsersRepository;
import com.example.demo.model.users.UserInfo;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報DAOを呼び出して使用
 * 
 * 
 * @author
 * 
 */

@Service
@RequiredArgsConstructor
public class LoginService {

	private final UsersRepository repository;
	
	/**
	 * 入力されたメールアドレスから、レコード検索
	 * 
	 * @param email 入力されたメールアドレス
	 * @return 取得したユーザー情報エンティティ群(1件のみ)
	 */

	
	public Optional<UserInfo> searchRepositoryByEmail(String email) {
		
		return repository.findByEmail(email);

		
	}
	
}
