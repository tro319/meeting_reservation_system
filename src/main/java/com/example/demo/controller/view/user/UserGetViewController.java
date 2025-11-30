package com.example.demo.controller.view.user;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

/*
 * ユーザー情報取得結果表示処理群
 * 
 * @author ys
 * 
 */

@Controller
@RequiredArgsConstructor

public class UserGetViewController {
	
	
	/*
	 * ユーザー情報表示処理
	 * 
	 * @param session セッション値情報
	 * @param model モデル値情報
	 * @return 対象テンプレートファイルパス
	 * 
	 */
	
	@GetMapping("/user/user_view")
	public String userView(HttpSession session, Model model) {
			
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		if (loginId == null) {
			
			return "redirect:/user/login";
			
		}
		
		
		
		return "user/user_view";
		
			
			

		
	}

}
