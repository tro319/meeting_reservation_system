package com.example.demo.model.form.user;

import lombok.Data;

/* 
 * 予約登録用フォームクラス
 * 
 * @author ys
 * 
 */

@Data

public class ReservationRegisterForm {
	
	private String date;
	
	private int timeId;
	
	private int userId;
	
	private int interviewerId;
	

}
