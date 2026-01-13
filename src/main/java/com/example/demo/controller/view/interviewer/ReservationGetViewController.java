package com.example.demo.controller.view.interviewer;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

/*
 * 予約情報取得結果表示処理群
 * 
 * @author ys
 * 
 */

@Controller("interviewerReservationGetViewController")
@RequiredArgsConstructor

public class ReservationGetViewController {
	
	
	/*
	 * 予約情報表示処理
	 * 
	 * @param session セッション値情報
	 * @param model モデル値情報
	 * @return 対象テンプレートファイルパス
	 * 
	 */
	
	@GetMapping("/interviewer/reservation_view")
	public String reservationView(HttpSession session, Model model) {
			
		Integer loginId = (Integer)session.getAttribute("log_interviewer_id");
		
		if (loginId == null) {
			
			return "redirect:/interviewer/login";
			
		}
		
		
		// f5更新にも対応
		
		if (!model.containsAttribute("reservation")) {
			
			Integer reservationId = (Integer)session.getAttribute("reservation_id");
			
			if (reservationId > 0) {
				
				session.removeAttribute("reservation_id");
				
				return "redirect:/interviewer/reservation?id=" + reservationId;
				
			}
			
			return "redirect:/interviewer/reservations";
			
		}
		
		
		return "interviewer/reservation_view";
			

		
	}

}
