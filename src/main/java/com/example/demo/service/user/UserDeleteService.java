package com.example.demo.service.user;

import org.springframework.stereotype.Service;

import com.example.demo.repository.user.UsersRepository;

import lombok.RequiredArgsConstructor;

/*
 * ユーザー削除処理群
 * 
 * @author ys
 * 
 */

@Service
@RequiredArgsConstructor

public class UserDeleteService {

	private final UsersRepository repository;
	
	
	/*
	 * ユーザー削除処理
	 * 
	 * @param id ログイン中Id
	 * 
	 */
	
	public void deleteUserById(Integer id) {
		
		repository.deleteById(id);
		
	}
	
}
