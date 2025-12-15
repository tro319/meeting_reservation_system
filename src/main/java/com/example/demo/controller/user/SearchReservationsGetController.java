package com.example.demo.controller.user;

import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.Reservation;
import com.example.demo.model.form.user.SearchForm;
import com.example.demo.service.user.ReservationsGetService;

import lombok.RequiredArgsConstructor;


/*
 * 予約一覧取得処理群 (検索後)
 * 
 * @author ys
 * 
 */

@Controller
@RequiredArgsConstructor

public class SearchReservationsGetController {
	
	private final ReservationsGetService service;
	
	
	/*　ユーザーの予約一覧取得処理 (検索後)
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @return 予約一覧表示のパスへ
	 * 
	 */
	
	@GetMapping("/user/reservations/search")
	
	public String getReservations(HttpSession session, RedirectAttributes redirectAttributes, SearchForm form) {
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		// ログインしているかどうか
		
		if (loginId == null) {
			
			return "redirect:/user/login";
			
		}
		
		
		
		List<Reservation> reservationInfos = service.getReservations(loginId);
		
		// 今日以降に絞り込み
		
		LocalDate today = LocalDate.now();
		
		reservationInfos = reservationInfos.stream()
							 .filter(reservation -> !reservation.getDate().isBefore(today))
							    .toList();
		
		
		// 各検索値で絞り込み
		
		
		if (form.getName() != null) {
			
			reservationInfos = reservationInfos.stream().filter(reservation -> reservation.getInterviewer().getName().contains(form.getName())).toList();
			
		}
		
		
		if (form.getTime() > 0) {
			
			reservationInfos = reservationInfos.stream().filter(reservation -> reservation.getTime().getStart()==form.getTime()).toList();
			
			
		}
		
		if (form.getDate() != null) {
			
			reservationInfos = reservationInfos.stream().filter(reservation -> reservation.getDate().toString().contains(form.getDate())).toList();
			
			
		}
		
		session.setAttribute("search_form", form);
		
		redirectAttributes.addFlashAttribute("reservations", reservationInfos);
		
		
		return "redirect:/user/reservations_view/search?name=" + form.getName() + "&date=" + form.getDate() + "&time=" + form.getTime();
		
		
	}

}
