package com.example.demo.model.users;

import lombok.Data;

/**
 *  ユーザー登録フォーム入力情報 エンティティ
 * 
 * @author ys
 * 
 */

@Data
public class SignUpFormInfo {
	
	private String userName;
	
	private String email;
	
	private String pass;
	
	private String repass;

}
