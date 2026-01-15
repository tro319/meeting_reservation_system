package com.example.demo.controller.view.user;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.form.user.SearchForm;

import lombok.RequiredArgsConstructor;

/*
 * 予約一覧取得結果表示処理群 (検索後)
 * 
 * @author ys
 * 
 */

@Controller
@RequiredArgsConstructor

public class SearchReservationsGetViewController {
	
	
	/*
	 * 予約一覧表示処理 (検索後)
	 * 
	 * @param session セッション値情報
	 * @param model モデル値情報
	 * @return 対象テンプレートファイルパス
	 * 
	 */
	
	@GetMapping("/user/reservations_view/search")
	public String reservationsView(HttpSession session, Model model) {
			
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		if (loginId == null) {
			
			return "redirect:/user/login";
			
		} else {
			
			
			// f5更新にも対応
			
			if (!model.containsAttribute("reservations")) {
				
				SearchForm searchForm = (SearchForm)session.getAttribute("search_form");
				
				if (searchForm != null) {
					
					return "redirect:/user/reservations/search?name=" + searchForm.getName() + "&date=" + searchForm.getDate() + "&time=" + searchForm.getTime();
					
				}
				
				return "redirect:/user/reservations";
				
					
				
			}
			
			
			return "user/reservations_view";
			
			
		}
		
		
			

		
	}

}
