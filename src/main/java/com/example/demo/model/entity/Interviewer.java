package com.example.demo.model.entity;



import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.Data;



/* 
 * 実施者エンティティクラス
 * 
 * @author ys
 * 
 */

@Entity
@Table(name="interviewers")
@Data

public class Interviewer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String kana;
	
	private String email;
	
	private String pass;
	
	@OneToMany(mappedBy="interviewer", cascade=CascadeType.ALL)
	private List<Reservation> reservations;
	
	@ManyToOne
	@JoinColumn(name="manager_id")
	private Manager manager;
	
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
