package com.example.demo.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

/* 
 * ユーザーエンティティクラス
 * 
 * @author ys
 * 
 */

@Entity
@Table(name="users")
@Data

public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String kana;
	
	private String email;
	
	private String pass;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	
}
