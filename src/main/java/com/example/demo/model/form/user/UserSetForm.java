package com.example.demo.model.form.user;

import lombok.Data;

/* 
 * ユーザー更新用フォームクラス
 * 
 * @author ys
 * 
 */

@Data

public class UserSetForm {
	
	private String name;
	
	private String kana;

	private String email;
	
	private String pass;
	
	private String repass;
	
	private String hidName;
	
	private String hidKana;
	
	private String hidEmail;
	
	private String hidPass;
	
}
