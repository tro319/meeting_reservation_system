package com.example.demo.service.user;

import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.User;
import com.example.demo.repository.user.UsersRepository;

import lombok.RequiredArgsConstructor;

/*
 * ユーザー情報更新処理群
 * 
 * @author ys
 * 
 */

@Service
@RequiredArgsConstructor

public class UserSetService {

	private final PasswordEncoder passEncoder;
	
	private final UsersRepository repository;
	
	
	/*
	 * idからユーザー情報絞り込み処理
	 * 
	 * @param id ログイン中id
	 * @return ユーザーエンティティ1件
	 * 
	 */
	
	public User getUser(Integer id) {
		
		User userInfo = repository.findById(id).orElseThrow( () -> new IllegalArgumentException("ユーザーが存在しません。") );
		
		return userInfo;
		
	}
	
	
	/*
	 * idと更新情報からユーザー情報更新処理
	 * 
	 * @param id ログイン中id
	 * @param updates 更新対象の情報群
	 * @return ユーザーエンティティ1件
	 * 
	 */
	
	public User setUser(Integer id, Map<String, String> updates) {
		
		User userInfo = repository.findById(id).orElseThrow( () -> new IllegalArgumentException("ユーザーが存在しません。") );
		
		updates.forEach( (key, value) -> {
			
			switch (key) {
			
				case "name":
					
					userInfo.setName(value);
					break;
					
				case "kana":
					
					userInfo.setKana(value);
					break;
					
				case "email":
					
					userInfo.setEmail(value);
					break;
					
				case "pass":
					
					String afterPass = passEncoder.encode(value);
					userInfo.setPass(afterPass);
					break;
					
			}
			
		});
		
		return repository.save(userInfo);
		
	}
	
}
