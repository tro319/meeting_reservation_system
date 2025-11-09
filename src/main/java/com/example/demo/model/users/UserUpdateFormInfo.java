package com.example.demo.model.users;

import lombok.Data;

/**
 *  ユーザー更新フォーム エンティティ
 * 
 * @author ys
 * 
 */

@Data
public class UserUpdateFormInfo {
	
	private int hidId;

	private String userName;
	
	private String email;
	
	private String hidUserName;
	
	private String hidEmail;

	
}
