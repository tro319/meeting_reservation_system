package com.example.demo.controller.interviewer;

import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.Reservation;
import com.example.demo.service.interviewer.ReservationsGetService;

import lombok.RequiredArgsConstructor;


/*
 * 予約一覧取得処理群
 * 
 * @author ys
 * 
 */

@Controller("interviewerReservationsGetController")
@RequiredArgsConstructor

public class ReservationsGetController {
	
	private final ReservationsGetService service;
	
	
	/*　実施者が担当する、今日以降の予約一覧取得処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @return 予約一覧表示のパスへ
	 * 
	 */
	
	@GetMapping("/interviewer/reservations")
	
	public String getReservations(HttpSession session, RedirectAttributes redirectAttributes) {
		
		Integer loginId = (Integer)session.getAttribute("log_interviewer_id");
		
		// ログインしているかどうか
		
		if (loginId == null) {
			
			return "redirect:/interviewer/login";
			
		}
		
		
		
		List<Reservation> reservationInfos = service.getReservations(loginId);
		
		// 今日以降に絞り込み
		
		LocalDate today = LocalDate.now();
		
		reservationInfos = reservationInfos.stream()
							 .filter(reservation -> !reservation.getDate().isBefore(today))
							    .toList();
	
		
		
		redirectAttributes.addFlashAttribute("reservations", reservationInfos);
		
		return "redirect:/interviewer/reservations_view";
		
		
	}

}
