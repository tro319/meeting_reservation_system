package com.example.demo.controller.view.interviewer;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.form.user.SearchForm;

import lombok.RequiredArgsConstructor;

/*
 * 予約一覧取得結果表示処理群
 * 
 * @author ys
 * 
 */

@Controller("interviewerReservationsGetViewController")
@RequiredArgsConstructor

public class ReservationsGetViewController {
	
	
	/*
	 * 予約一覧表示処理
	 * 
	 * @param session セッション値情報
	 * @param model モデル値情報
	 * @return 対象テンプレートファイルパス
	 * 
	 */
	
	@GetMapping("/interviewer/reservations_view")
	public String reservationsView(HttpSession session, Model model) {
			
		Integer loginId = (Integer)session.getAttribute("log_interviewer_id");
		
		if (loginId == null) {
			
			return "redirect:/interviewer/login";
			
		}
		
		
		// f5更新にも対応
		
		if (!model.containsAttribute("reservations")) {
			
			return "redirect:/interviewer/reservations";
			
		}
		
		SearchForm searchForm = new SearchForm();
		
		model.addAttribute("search_form", searchForm);
		
		return "interviewer/reservations_view";
			

		
	}

}
