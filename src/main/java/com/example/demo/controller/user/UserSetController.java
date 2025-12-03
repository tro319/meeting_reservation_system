package com.example.demo.controller.user;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.User;
import com.example.demo.model.form.user.UserSetForm;
import com.example.demo.service.user.UserSetService;

import lombok.RequiredArgsConstructor;

/*
 * ユーザー更新処理群
 * 
 * @author ys
 * 
 */

@Controller
@RequiredArgsConstructor

public class UserSetController {
	
	private final UserSetService service;
	
	private final PasswordEncoder passEncoder;
	
	/*
	 * ユーザー情報取得処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param form ユーザー更新用フォーム
	 * @return 取得結果表示へ
	 * 
	 */
	
	@GetMapping("/user/user_update")
	public String getUser(HttpSession session, RedirectAttributes redirectAttributes, UserSetForm form) {
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		if (loginId == null) {
			
			return "redirect:/user/login";
			
		}
		
		UserSetForm setForm = (UserSetForm)session.getAttribute("user_set_data");
		
		String setResult = (String)session.getAttribute("set_result");
		
		
		if (setForm == null && setResult == null) {
			
			User userInfo = service.getUser(loginId);
			
			setForm = new UserSetForm();
			
			setForm.setName(userInfo.getName());
			
			setForm.setKana(userInfo.getKana());
			
			setForm.setEmail(userInfo.getEmail());
			
			setForm.setPass(userInfo.getPass());
			
			setForm.setHidName(userInfo.getName());
			
			setForm.setHidKana(userInfo.getKana());
			
			setForm.setHidEmail(userInfo.getEmail());
			
			
			
		}
		
		if (setResult != null) {
			
			redirectAttributes.addFlashAttribute("set_result", setResult);
			
		}
		
		redirectAttributes.addFlashAttribute("user_set_data", setForm);
		
		return "redirect:/user/user_update_view";
		
		
	}
	
	
	/*
	 * ユーザー情報更新処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param form ユーザー更新用フォーム入力情報
	 * @return 更新後取得結果表示へ
	 * 
	 */

	@PostMapping("/user/user_update")
	public String setUser(HttpSession session, RedirectAttributes redirectAttributes, UserSetForm form) {
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		if (loginId == null) {
			
			return "redirect:/user/login";
			
		}
		
		session.setAttribute("user_set_data", form);
		
		if (!form.getPass().equals(form.getRepass())) {
			
			session.setAttribute("set_result", "パスワード入力が一致しません");
			
			return "redirect:/user/user_update";
			
		}	
		
		Map<String, String> updates = new HashMap<>();
		
		if (!form.getName().equals(form.getHidName())) {
		
			updates.put("name", form.getName());
			
		}
		
		if (!form.getKana().equals(form.getHidKana())) {
			
			updates.put("kana", form.getKana());
			
		}
		
		if (!form.getEmail().equals(form.getHidEmail())) {
			
			updates.put("email", form.getEmail());
			
		}
	
		
			
		if (updates.size() == 0) {
			
			session.setAttribute("set_result", "ユーザー情報が更新されていません");
		
			return "redirect:/user/user_update";
			
		}
		
		updates.put("pass", form.getPass());
		
		User setUser = service.setUser(loginId, updates);
		
		session.setAttribute("set_user", setUser);
		
		session.setAttribute("set_result", "ユーザー情報更新しました");
		
		session.removeAttribute("user_set_data");
		
		return "redirect:/user/user_get";
		
		
	}
	
	
	/*
	 * ユーザー情報更新フォーム取得へ戻る処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @return ユーザー情報取得処理へ
	 * 
	 */

	@GetMapping("/user/user_update/return")
	public String back(HttpSession session, RedirectAttributes redirectAttributes) {
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		if (loginId == null) {
			
			return "redirect:/user/login";
			
		}
		
		
		return "redirect:/user/user_update";
		
		
		
	}

}

