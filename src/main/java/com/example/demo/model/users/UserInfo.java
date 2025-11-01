package com.example.demo.model.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// TODO DBと両方にUNIQUE制約つけて反映 対象カラム名: email, user_name (tamapon)
	private String email;
	
	private String pass;
	
	@Column(name = "user_name")
	private String userName;

}
