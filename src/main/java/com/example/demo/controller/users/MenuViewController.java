package com.example.demo.controller.users;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.users.UserInfo;

/**
 * メニュー選択ページを表示させる
 * 
 * @author ys
 * 
 * 
 */

@Controller
public class MenuViewController {

	/**
	 * 
	 * 
	 * ログイン中ユーザー会員情報確認画面表示
	 * 
	 * @param model モデル
	 * @param session セッション情報
	 * @return 未ログイン時 ログインページへ | 成功時 ユーザー情報取得画面への遷移パス
	 * 
	 * 
	 */
	
	@GetMapping("/users/user_result")
	public String userInfoView(Model model ,HttpSession session) {
		UserInfo userInfoGet = (UserInfo)session.getAttribute("userGetResult");
		model.addAttribute("userInfoResult", userInfoGet);
		
		if (model.getAttribute("succ1") != null) {
			
			String signUpSucc1 = (String)model.getAttribute("succ1");
			System.out.println(2 + signUpSucc1);
			model.addAttribute("signUpSucc1", signUpSucc1);
			
		}
		
	
		return "users/user_result";
		
	}
	
	
	/**
	 * 
	 * 
	 * メニュー画面表示
	 * 
	 * 
	 * @return テンプレートファイルのパス
	 * 
	 * 
	 */
	
	@GetMapping("/users/menu")
	public String menuView() {
		
		return "users/menu";
		
	}
	
}
