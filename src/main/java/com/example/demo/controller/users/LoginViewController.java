package com.example.demo.controller.users;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.common.LoginFormInfo;

import lombok.RequiredArgsConstructor;


/**
 * ログイン画面表示処理
 * 
 *@author ys 
 * 
 */

@Controller
@RequiredArgsConstructor
public class LoginViewController {
	

	
	/**
	 * ログイン画面、初期表示
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return テンプレートファイルへのパス
	 * 
	 */
	
	@GetMapping("/users/login")
	public String loginView(Model model, HttpSession session, LoginFormInfo form) {
		
		Integer logID = (Integer)session.getAttribute("userLoginId");
		
		String logoutResult = (String)model.getAttribute("logoutResult");
		
		
		if (logoutResult != null) {
			model.addAttribute("logoutResult", logoutResult);
		}
		
		
		if (logID != null) {
			
			return "redirect:/users/menu";
			
		}
		
		String repassEmailSucc1 = (String)model.getAttribute("repassEmailSucc1");
		
		String updatePassResult = (String)model.getAttribute("setPassResult");
		
		String deleteUserResult = (String)model.getAttribute("userDeleteResult");
		
		if (repassEmailSucc1 != null) {
			
			model.addAttribute("repassEmailSucc1", repassEmailSucc1);
			
		} else if (updatePassResult != null){
			
			System.out.println(updatePassResult);
			model.addAttribute("setPassResult", updatePassResult);
			
		} else if (deleteUserResult != null) {
			
			model.addAttribute("userDeleteResult", deleteUserResult);
			
		}
		
		return "users/login";
		
	}
	
	
	
	
	
}
