package com.example.demo.controller.manager;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.manager.InterviewerDeleteService;

import lombok.RequiredArgsConstructor;

/*
 * 実施者削除処理群
 * 
 * @author ys
 * 
 */

@Controller("managerInterviewerDeleteController")
@RequiredArgsConstructor

public class InterviewerDeleteController {
	
	private final InterviewerDeleteService service;
	
	/*
	 * 実施者削除処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param id 選択された実施者id
	 * @return 実施者一覧取得へ
	 * 
	 */
	
	@GetMapping("/manager/interviewer_delete")
	public String deleteInterviewer(HttpSession session, RedirectAttributes redirectAttributes, @RequestParam(required=false) int id) {
		
		Integer loginId = (Integer)session.getAttribute("log_manager_id");
		
		// ログインしていなければ
		
		if (loginId == null) {
			
			return "redirect:/manager/login";
			
		}
		
		if (id <= 0) {
			
			return "redirect:/manager/interviewers";
			
		}
		
		Integer interviewerId = id;
		
		service.deleteInterviewer(interviewerId);
		
		
		session.setAttribute("delete_result", "削除が完了しました!");
		
		return "redirect:/manager/interviewers";
		
	}

}
