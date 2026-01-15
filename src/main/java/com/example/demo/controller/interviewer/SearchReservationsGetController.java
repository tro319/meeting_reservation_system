package com.example.demo.controller.interviewer;

import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.Reservation;
import com.example.demo.model.form.interviewer.SearchForm;
import com.example.demo.service.interviewer.ReservationsGetService;

import lombok.RequiredArgsConstructor;


/*
 * 予約一覧取得処理群 (検索後)
 * 
 * @author ys
 * 
 */

@Controller("interviewerSearchReservationsGetController")
@RequiredArgsConstructor

public class SearchReservationsGetController {
	
	private final ReservationsGetService service;
	
	
	/*　実施者が担当する、予約一覧取得処理 (検索後)
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param form 検索フォーム入力情報
	 * @return 予約一覧表示のパスへ
	 * 
	 */
	
	@GetMapping("/interviewer/reservations/search")
	
	public String getReservations(HttpSession session, RedirectAttributes redirectAttributes, SearchForm form) {
		
		Integer loginId = (Integer)session.getAttribute("log_interviewer_id");
		
		// ログインしているかどうか
		
		if (loginId == null) {
			
			return "redirect:/interviewer/login";
			
		}
		
		// 検索フォームが空なのか
		
		if ((form.getName() == null || form.getName().isEmpty()) && (form.getDate() == null || form.getDate().isEmpty())&& (form.getTime() == 0)) {
			
			return "redirect:/interviewer/reservations";
			
		}
		
		
		
		
		List<Reservation> reservationInfos = service.getReservations(loginId);
		
		// 今日以降に絞り込み
		
		LocalDate today = LocalDate.now();
		
		reservationInfos = reservationInfos.stream()
							 .filter(reservation -> !reservation.getDate().isBefore(today))
							    .toList();
		
		
		// 各検索値で絞り込み
		
		
		if (form.getName() != null && !form.getName().isEmpty()) {
			
			reservationInfos = reservationInfos.stream().filter(reservation -> reservation.getUser().getName().contains(form.getName()) || reservation.getUser().getKana().contains(form.getName()) ).toList();
			
		}
		
		
		if (form.getTime() > 0) {
			
			reservationInfos = reservationInfos.stream().filter(reservation -> reservation.getTime().getStart()==form.getTime()).toList();
			
			
		}
		
		if (form.getDate() != null && !form.getDate().isEmpty()) {
			
			LocalDate searchDate = LocalDate.parse(form.getDate());
			
			reservationInfos = reservationInfos.stream().filter(reservation -> reservation.getDate().equals(searchDate)).toList();
			
			
		}
		
		session.setAttribute("search_form", form);
		
		redirectAttributes.addFlashAttribute("search_form", form);
		
		redirectAttributes.addFlashAttribute("reservations", reservationInfos);
		
		
		return "redirect:/interviewer/reservations_view/search";
		
		
	}

}
