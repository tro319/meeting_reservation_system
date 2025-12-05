package com.example.demo.controller.view.user;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.form.user.UserSetForm;

import lombok.RequiredArgsConstructor;

/*
 * ユーザー更新表示処理群
 * 
 * @author ys
 * 
 */

@Controller
@RequiredArgsConstructor

public class UserSetViewController {

	/*
	 * ユーザー更新入力画面表示処理
	 * 
	 * @param session セッション値情報
	 * @param model モデル値情報
	 * @param form ユーザー更新用フォーム
	 * @return 対象のテンプレートへのパス
	 * 
	 */
	
	@GetMapping("/user/user_update_view")
	public String viewSet(HttpSession session, Model model, UserSetForm form) {
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		
		// ログインしているかどうか
		
		if (loginId == null) {
			
			return "redirect:/user/login";
			
		} 
		
		// f5更新対策
		
		if (!model.containsAttribute("user_set_data")) {
			
			return "redirect:/user/user_update";
			
		}
		
		
		return "user/user_update_view";
		
		
	}
	
	
}
