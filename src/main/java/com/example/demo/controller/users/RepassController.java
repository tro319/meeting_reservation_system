package com.example.demo.controller.users;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.users.RepassFormInfo;
import com.example.demo.service.users.SignUpService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RepassController {
	
	// インスタンス定義
	
	private final SignUpService signUpService;
	
	
	/**
	 * パスワード再設定フォームから更新処理に繋げる処理
	 * 
	 * @param redirectAttributes リダイレクト時値保持
	 * @param session セッション情報
	 * @param パスワード再設定フォーム入力情報
	 * @return 成功時 ログインページへの遷移パス | 失敗時 現在のページで遷移維持
	 * 
	 */
	@PostMapping("/users/signup/repass")
	public String repassResister(RedirectAttributes redirectAttributes, HttpSession session, RepassFormInfo form) {
		
		String newPass = form.getPass();
		
		// セッションから対象ユーザーid取得
		
		Integer repassUserID = (Integer)session.getAttribute("repassUserID");
		
		String newPassResult = signUpService.setNewPass(repassUserID, newPass);
		
		if (newPassResult != null) {
			
			redirectAttributes.addFlashAttribute("setPassResult", newPassResult);
			return "redirect:/users/login";
			
		}
		
		redirectAttributes.addFlashAttribute("setPassResult", "パスワードの更新に失敗しました");
		return "redirect:/signup/repass";
		
	}
	
}
