package com.example.demo.controller.manager;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.Interviewer;
import com.example.demo.service.manager.InterviewersGetService;

import lombok.RequiredArgsConstructor;


/*
 * 実施者一覧取得処理群
 * 
 * @author ys
 * 
 */

@Controller("managerInterviewersGetController")
@RequiredArgsConstructor

public class InterviewersGetController {
	
	private final InterviewersGetService service;
	
	
	/*　実施者一覧取得処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @return 実施者一覧表示のパスへ
	 * 
	 */
	
	@GetMapping("/manager/interviewers")
	
	public String getInterviewers(HttpSession session, RedirectAttributes redirectAttributes) {
		
		Integer loginId = (Integer)session.getAttribute("log_manager_id");
		
		// ログインしているかどうか
		
		if (loginId == null) {
			
			return "redirect:/manager/login";
			
		}
		
		
		
		List<Interviewer> interviewerInfos = service.getInterviewers();
		
		
		redirectAttributes.addFlashAttribute("interviewers", interviewerInfos);
		
		return "redirect:/manager/interviewers_view";
		
		
	}

}
