package com.example.demo.controller.interviewer;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.Interviewer;
import com.example.demo.service.interviewer.InterviewerGetService;

import lombok.RequiredArgsConstructor;


/*
 * 実施者情報取得処理群
 * 
 * @author ys
 * 
 */

@Controller("interviewerGetController")
@RequiredArgsConstructor

public class InterviewerGetController {
	
	private final InterviewerGetService service;
	
	/*
	 * 実施者情報取得処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @return 取得結果表示へ
	 * 
	 */
	
	@GetMapping("/interviewer/interviewer_get")
	public String getUser(HttpSession session, RedirectAttributes redirectAttributes) {
		
		Integer loginId = (Integer)session.getAttribute("log_interviewer_id");
		
		if (loginId == null) {
			
			return "redirect:/interviewer/login";
			
		}
		
		Interviewer interviewerInfo = service.getInterviewer(loginId);
		
		
		redirectAttributes.addFlashAttribute("interviewer", interviewerInfo);
		
		return "redirect:/interviewer/interviewer_view";
			
		
	}
	

}
