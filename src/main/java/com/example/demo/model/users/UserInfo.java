package com.example.demo.model.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

/**
 *  ユーザー情報テーブル エンティティ
 * 
 * @author ys
 * 
 */

@Entity
@Table(name = "users")
@Data
public class UserInfo {
	
	@Id
	private int userID;
	
	private String email;
	
	private String pass;
	
	@Column(name = "user_name")
	private String userName;

}
