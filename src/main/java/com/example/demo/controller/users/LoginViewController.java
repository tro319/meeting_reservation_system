package com.example.demo.controller.users;

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
	public String loginView(Model model, LoginFormInfo form) {
		
		String repassEmailSucc1 = (String)model.getAttribute("repassEmailSucc1");
		
		String updatePassResult = (String)model.getAttribute("setPassResult");
		
		if (repassEmailSucc1 != null) {
			
			model.addAttribute("repassEmailSucc1", repassEmailSucc1);
			
		} else if (updatePassResult != null){
			
			System.out.println(updatePassResult);
			model.addAttribute("setPassResult", updatePassResult);
			
		}
		
		return "users/login";
		
	}
	
	
	
	
	
}
