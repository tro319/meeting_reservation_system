package com.example.demo.controller.user;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.Reservation;
import com.example.demo.service.user.ReservationGetService;

import lombok.RequiredArgsConstructor;


/*
 * 予約取得処理群
 * 
 * @author ys
 * 
 */

@Controller
@RequiredArgsConstructor

public class ReservationGetController {
	
	private final ReservationGetService service;
	
	
	/*　予約取得処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @return 予約一覧表示のパスへ
	 * 
	 */
	
	@GetMapping("/user/reservation")
	
	public String getReservation(HttpSession session, RedirectAttributes redirectAttributes, @RequestParam int id) {
		
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		// ログインしているかどうか
		
		if (loginId == null) {
			
			return "redirect:/user/login";
			
		}
		
		
		Integer reservationId = (Integer)id;
		
		Reservation reservationInfo = service.getReservation(reservationId);
		
		redirectAttributes.addFlashAttribute("reservation", reservationInfo);
		
		session.setAttribute("reservation_id", reservationId);
		
		return "redirect:/user/reservation_view";
		
		
	}

}
