package com.example.demo.service.user;

import org.dozer.DozerBeanMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.User;
import com.example.demo.model.form.user.SignupForm;
import com.example.demo.repository.user.UsersRepository;

import lombok.RequiredArgsConstructor;

/* 
 * ユーザー登録用サービスクラス
 * 
 * @author ys
 * 
 */

@Service
@RequiredArgsConstructor

public class SignupService {

	
	private final UsersRepository repository;
	
	private final PasswordEncoder passEncoder;
	
	private final DozerBeanMapper mapper;
	
	
	/*
	 * ユーザー登録処理
	 * 
	 * @param form ユーザー登録用フォーム
	 * @return ユーザーエンティティ1件
	 * 
	 */
	
	public User register(SignupForm form) {
		
		User userInfo = mapper.map(form, User.class);
		
		String encodedPass = passEncoder.encode(form.getPass());
		
		userInfo.setPass(encodedPass);
		
		return repository.save(userInfo);
		
	}
	
	
	/*
	 * ユーザー重複チェック処理
	 * 
	 * @param form ユーザー登録用フォーム
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
