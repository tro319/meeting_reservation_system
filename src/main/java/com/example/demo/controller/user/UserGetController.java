package com.example.demo.controller.user;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.User;
import com.example.demo.service.user.UserGetService;

import lombok.RequiredArgsConstructor;


/*
 * ユーザー情報取得処理群
 * 
 * @author ys
 * 
 */

@Controller
@RequiredArgsConstructor

public class UserGetController {
	
	private final UserGetService service;
	
	/*
	 * ユーザー情報取得処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @return 取得結果表示へ
	 * 
	 */
	
	@GetMapping("/user/user_get")
	public String getUser(HttpSession session, RedirectAttributes redirectAttributes) {
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		if (loginId == null) {
			
			return "redirect:/user/login";
			
		}
		
		User userInfo = (User)session.getAttribute("signup_user");
		
		String signupResult = (String)session.getAttribute("signup_result");
		
		
		// ユーザー登録直後かどうか
		
		if (userInfo != null && signupResult != null) {
			
			redirectAttributes.addFlashAttribute("user", userInfo);
			
			redirectAttributes.addFlashAttribute("signup_result", signupResult);
			
			session.removeAttribute("signup_user");
			
			session.removeAttribute("signup_result");
			
			return "redirect:/user/user_view";
			
		}
		
		userInfo = service.getUserById(loginId);
		
		redirectAttributes.addFlashAttribute("user", userInfo);
		
		return "redirect:/user/user_view";
			
		
	}
	

}
