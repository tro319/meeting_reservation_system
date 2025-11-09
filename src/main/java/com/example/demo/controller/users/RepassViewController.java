package com.example.demo.controller.users;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.users.RepassFormInfo;

import lombok.RequiredArgsConstructor;

/**
 * 
 * 
 * パスワード更新画面表示処理
 * 
 * @author ys
 * 
 * 
 */

@Controller
@RequiredArgsConstructor
public class RepassViewController {

	/**
	 * 
	 * 
	 * パスワード更新画面表示
	 * 
	 * @param set URL末尾のハッシュ値
	 * @param model モデル
	 * @param session セッション情報
	 * @param RepassFormInfo パスワード更新フォーム入力情報
	 * @return ハッシュ値不一致 ログインページへ | ハッシュ値一致 テンプレートファイルへのパス
	 * 
	 * 
	 */
	
	@GetMapping("/users/signup/repass")
	public String repassFormView(@RequestParam String set, Model model, HttpSession session, RepassFormInfo form) {
		
		String checkHash = (String)session.getAttribute("repassURLHash");
		
		String updatePassResult = (String)model.getAttribute("updatePassResult");
		
		
		if (!checkHash.equals(set)) {
			
			System.out.println(set);
			System.out.println(checkHash);
			
			return "redirect:/users/login";
			
		}
		
		
		if (updatePassResult != null){
				
			model.addAttribute("setPassResult", updatePassResult);
				
		}
		
		
		return "users/pass_update_form";
		
	}
	
	
}
