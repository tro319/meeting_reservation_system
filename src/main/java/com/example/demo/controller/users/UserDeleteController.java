package com.example.demo.controller.users;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.users.LoginService;

import lombok.RequiredArgsConstructor;

/**
 * 
 * 
 * 会員退会処理につなげる処理
 * 
 * @author ys
 * 
 * 
 */

@Controller
@RequiredArgsConstructor
public class UserDeleteController {
	
	// インスタンス定義
	
	private final LoginService service;
	
	
	/**
	 * 
	 * 
	 * 会員情報更新処理に繋げる処理
	 * 
	 * @param model モデル
	 * @param session セッション情報
	 * @param redirectAttributes リダイレクト時値保持
	 * @param form 会員情報更新フォーム入力情報
	 * @return 失敗時 そのままの画面維持パス | 成功時 会員情報詳細画面への遷移パス
	 * 
	 * 
	 */
	
	@GetMapping("/users/user_delete")
	public String userDelete(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		
		
		Integer userLoginID = (Integer)session.getAttribute("userLoginId");
		
		if (userLoginID == null) {
			
			
			return "redirect:/users/login";
			
		}
		
		
		String userDeleteResult = service.deleteUserById(userLoginID);
		
		session.removeAttribute("userLoginId");
		
		redirectAttributes.addFlashAttribute("userDeleteResult", userDeleteResult);
		
		return "redirect:/users/login";	
		
	}

	

	
	
}
