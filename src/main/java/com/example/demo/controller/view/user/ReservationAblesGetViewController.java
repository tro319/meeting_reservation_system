package com.example.demo.controller.view.user;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

/*
 * 予約空き状況一覧取得結果表示処理群
 * 
 * @author ys
 * 
 */

@Controller("userReservationAblesGetViewController")
@RequiredArgsConstructor

public class ReservationAblesGetViewController {
	
	
	/*
	 * 予約空き状況一覧表示処理
	 * 
	 * @param session セッション値情報
	 * @param model モデル値情報
	 * @return 対象テンプレートファイルパス
	 * 
	 */
	
	@GetMapping("/user/reservation_ables_view")
	public String reservationAblesView(HttpSession session, Model model) {
			
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		if (loginId == null) {
			
			return "redirect:/user/login";
			
		}
		
		
		// f5更新にも対応
		
		if (!model.containsAttribute("reservation_ables")) {
			
			Integer interviewerId = (Integer)session.getAttribute("selected_interviewer_id");
			
			if (interviewerId > 0) {
				
				return "redirect:/user/reservation_ables_get?interviewer_id=" + interviewerId;
				
			}
			
			return "redirect:/user/interviewers";
			
		}
		
	
		
		return "user/reservation_ables_view";
			

		
	}

}
