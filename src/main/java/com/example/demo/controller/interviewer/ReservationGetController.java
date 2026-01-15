package com.example.demo.controller.interviewer;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.Reservation;
import com.example.demo.service.interviewer.ReservationGetService;

import lombok.RequiredArgsConstructor;


/*
 * 予約取得処理群
 * 
 * @author ys
 * 
 */

@Controller("interviewerReservationGetController")
@RequiredArgsConstructor

public class ReservationGetController {
	
	private final ReservationGetService service;
	
	
	/*　予約取得処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param id 指定された予約id
	 * @return 予約情報表示のパスへ
	 * 
	 */
	
	@GetMapping("/interviewer/reservation")
	
	public String getReservation(HttpSession session, RedirectAttributes redirectAttributes, @RequestParam int id) {
		
		
		Integer loginId = (Integer)session.getAttribute("log_interviewer_id");
		
		// ログインしているかどうか
		
		if (loginId == null) {
			
			return "redirect:/interviewer/login";
			
		}
		
		
		Integer reservationId = (Integer)id;
		
		Reservation reservationInfo = service.getReservation(reservationId);
		
		redirectAttributes.addFlashAttribute("reservation", reservationInfo);
		
		session.setAttribute("reservation_id", reservationId);
		
		return "redirect:/interviewer/reservation_view";
		
		
	}

}
