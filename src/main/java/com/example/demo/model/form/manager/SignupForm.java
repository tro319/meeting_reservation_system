package com.example.demo.model.form.manager;

import lombok.Data;

/* 
 * 実施者登録用フォームクラス
 * 
 * @author ys
 * 
 */

@Data

public class SignupForm {
	
	
	private String name;
	
	private String kana;
	
	private String email;
	
	private String pass;
	
	private String repass;
	

}
