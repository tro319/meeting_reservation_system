package com.example.demo.service.users;

import org.dozer.DozerBeanMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.users.UsersRepository;
import com.example.demo.model.users.SignUpFormInfo;
import com.example.demo.model.users.UserInfo;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報DAOを呼び出して使用
 * 
 * 
 * @author ys
 * 
 */

@Service
@RequiredArgsConstructor
public class SignUpService {

	private final UsersRepository repository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final DozerBeanMapper mapper;
	
	/**
	 * 会員登録処理
	 * 
	 * @param 入力値
	 * @return 登録情報(対象のユーザー情報1件)
	 * 
	 */

	
	public UserInfo register(SignUpFormInfo form) {
		
		
		var userInfo = mapper.map(form, UserInfo.class);
		
		var encodedPass = passwordEncoder.encode(form.getPass());
		
		userInfo.setPass(encodedPass);
		
		return repository.save(userInfo);

		
	}
	
	
	/**
	 * 会員登録重複チェック処理
	 * 
	 * @param 入力値 (email, userName)
	 * @return 既に存在したかを表すT/F
	 * 
	 */

	
	public Boolean confirm(String email, String userName) {
		
		Boolean emailCount = repository.existsByEmail(email);
		
		Boolean userNameCount = repository.existsByUserName(userName);
		
		
		return emailCount || userNameCount;

		
	}
	
}
