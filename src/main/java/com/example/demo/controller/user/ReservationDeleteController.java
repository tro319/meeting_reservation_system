package com.example.demo.controller.user;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.user.ReservationDeleteService;

import lombok.RequiredArgsConstructor;


/*
 * 予約キャンセル処理群
 * 
 * @author ys
 * 
 */

@Controller
@RequiredArgsConstructor

public class ReservationDeleteController {
	
	private final ReservationDeleteService service;
	
	
	/*　予約キャンセル処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @return 予約一覧取得処理のパスへ
	 * 
	 */
	
	@GetMapping("/user/reservation_delete")
	
	public String deleteReservation(HttpSession session, @RequestParam int id) {
		
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		// ログインしているかどうか
		
		if (loginId == null) {
			
			return "redirect:/user/login";
			
		}
		
		Integer reservationId = (Integer)id;
		
		service.deleteReservation(reservationId);
		
		session.setAttribute("reservation_delete_result", "予約をキャンセルしました");
		
		return "redirect:/user/reservations";
		
		
	}

}
