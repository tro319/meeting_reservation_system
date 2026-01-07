package com.example.demo.model.form.manager;

import lombok.Data;

/* 
 * 実施者更新用フォームクラス
 * 
 * @author ys
 * 
 */

@Data

public class InterviewerSetForm {
	
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
