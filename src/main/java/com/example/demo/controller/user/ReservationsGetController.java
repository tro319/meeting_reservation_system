package com.example.demo.controller.user;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.Reservation;
import com.example.demo.service.user.ReservationsGetService;

import lombok.RequiredArgsConstructor;


/*
 * 予約一覧取得処理群
 * 
 * @author ys
 * 
 */

@Controller
@RequiredArgsConstructor

public class ReservationsGetController {
	
	private final ReservationsGetService service;
	
	
	/*　ユーザーの予約取得処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @return 予約一覧表示のパスへ
	 * 
	 */
	
	@GetMapping("/user/reservations")
	
	public String getReservations(HttpSession session, RedirectAttributes redirectAttributes) {
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		// ログインしているかどうか
		
		if (loginId == null) {
			
			return "redirect:/user/login";
			
		}
		
		
		
		List<Reservation> reservationInfos = service.getReservations(loginId);
		
		// テスト表示
		
		for (Reservation rese : reservationInfos) {
			
			System.out.println(rese.getUser().getName());
			
		}
		
		
		redirectAttributes.addFlashAttribute("reservations", reservationInfos);
		
		return "redirect:/user/reservations_view";
		
		
	}

}
