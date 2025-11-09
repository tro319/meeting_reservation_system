package com.example.demo.controller.users;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.users.UserUpdateFormInfo;
import com.example.demo.service.users.SignUpService;

import lombok.RequiredArgsConstructor;

/**
 * 
 * 
 * 会員情報更新処理
 * 
 * @author ys
 * 
 * 
 */

@Controller
@RequiredArgsConstructor
public class UserUpdateController {
	
	// インスタンス定義
	
	private final SignUpService signUpService;
	
	
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
	
	@PostMapping("/users/user_update")
	public String userUpdate(Model model, HttpSession session, RedirectAttributes redirectAttributes, UserUpdateFormInfo form) {
		
		Integer hidID = form.getHidId();
		
		String hidUserName = form.getHidUserName();
		
		String hidEmail = form.getHidEmail();
		
		
		String updatedUserName = form.getUserName();
		
		String updatedEmail = form.getEmail();
		
		
		if (hidUserName.equals(updatedUserName) && hidEmail.equals(updatedEmail)) {
			
			redirectAttributes.addFlashAttribute("updateResult", "入力値が更新前から変更されていません");
			
			return "redirect:/users/user_update";
			
		}
		
		System.out.println(hidUserName);
		
		System.out.println(hidEmail);
		
		
		String updateResult = signUpService.setUser(hidID, updatedUserName, updatedEmail);
		
		redirectAttributes.addFlashAttribute("updateResult", updateResult);
		
		return "redirect:/users/user_info";
		
		
		
		
	}

	

	
	
}
