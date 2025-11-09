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
	 * @param form ユーザー登録フォーム入力値
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
	 * @param email 入力されたメールアドレス
	 * @param userName 入力されたユーザーネーム
	 * @return 既に存在したかを表すT/F
	 * 
	 */

	
	public Boolean confirm(String email, String userName) {
		
		Boolean emailCount = repository.existsByEmail(email);
		
		Boolean userNameCount = repository.existsByUserName(userName);
		
		
		return emailCount || userNameCount;

		
	}
	
	
	/**
	 * パスワード再設定url送信時、既存メールアドレスかのチェック処理
	 * 
	 * @param email 入力されたメールアドレス
	 * @return 存在したかを表すT/F
	 * 
	 */
	
	public Boolean repassCheckEmail(String email) {
		
		Boolean emailCount = repository.existsByEmail(email);
		
		
		return emailCount;

		
	}
	
	
	/**
	 * パスワード再設定処理
	 * 
	 * @param id 対象ユーザーID
	 * @param newPass 新しいパスワード
	 * @return 更新結果
	 * 
	 */
	
	public String setNewPass(Integer id, String newPass) {
		
		String updatePassResult = null;
		
		String hashedPass = passwordEncoder.encode(newPass);
		
		UserInfo repassUser = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("ユーザーが存在しません"));
	
		
		repassUser.setPass(hashedPass);
		
		repository.save(repassUser);
		
		updatePassResult = "パスワード更新しました";
		
		return updatePassResult;

		
	}
	
	
	
	/**
	 * ユーザー情報更新処理
	 * 
	 * @param id 対象ユーザーID
	 * @param userName 入力された新しいユーザーネーム
	 * @param email 入力された新しいメールアドレス
	 * @return 更新結果
	 * 
	 */
	
	public String setUser(Integer id, String newUserName, String newEmail) {
		
		String updateUserResult = null;
		
		
		UserInfo updateUser = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("ユーザーが存在しません"));
	
		updateUser.setUserName(newUserName);
		
		updateUser.setEmail(newEmail);
		
		repository.save(updateUser);
		
		updateUserResult = "会員情報更新しました";
		
		return updateUserResult;

		
	}
	
	
	
}
