package com.example.demo.model.entity;



import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.Data;

/* 
 * 時間エンティティクラス
 * 
 * @author ys
 * 
 */

@Entity
@Table(name="times")
@Data

public class Time {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int start;
	
	@OneToMany(mappedBy="time", cascade=CascadeType.ALL)
	private List<Reservation> reservations;
	
	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
	
	
	@PrePersist 
	public void onCreate() {
		
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		
	}
	
	@PreUpdate
	public void onUpdate() {
		
		this.updatedAt = LocalDateTime.now();
		
	}
	

}
