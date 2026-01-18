package com.example.demo.controller.view.user;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.entity.Interviewer;
import com.example.demo.model.entity.Time;
import com.example.demo.model.entity.User;
import com.example.demo.model.form.user.ReservationRegisterForm;

import lombok.RequiredArgsConstructor;

/*
 * 予約登録表示処理群
 * 
 * @author ys
 * 
 */

@Controller
@RequiredArgsConstructor

public class ReservationRegisterViewController {
	
	
	/*
	 * 予約登録表示処理
	 * 
	 * @param session セッション値情報
	 * @param model モデル値情報
	 * @return 対象テンプレートファイルパス
	 * 
	 */
	
	@GetMapping("/user/reservation_register_view")
	public String confirmView(HttpSession session, Model model) {
			
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		if (loginId == null) {
			
			return "redirect:/user/login";
			
		}
		
		
		ReservationRegisterForm registerForm = (ReservationRegisterForm)session.getAttribute("reservation_register_form");
		
		String date = (String)session.getAttribute("reservation_date");
		
		Time timeInfo = (Time)session.getAttribute("reservation_time");
		
		User userInfo = (User)session.getAttribute("reservation_user");
		
		Interviewer interviewerInfo = (Interviewer)session.getAttribute("reservation_interviewer");
		
		model.addAttribute("reservation_register_form", registerForm);
		
		model.addAttribute("reservation_date", date);
		
		model.addAttribute("reservation_time", timeInfo);
		
		model.addAttribute("reservation_user", userInfo);
		
		model.addAttribute("reservation_interviewer", interviewerInfo);
		
		return "user/reservation_register_view";
			

		
	}

}
