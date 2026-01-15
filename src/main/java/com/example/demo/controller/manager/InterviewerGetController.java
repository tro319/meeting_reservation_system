package com.example.demo.controller.manager;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.Interviewer;
import com.example.demo.service.manager.InterviewerGetService;

import lombok.RequiredArgsConstructor;


/*
 * 実施者情報取得処理群
 * 
 * @author ys
 * 
 */

@Controller("managerInterviewerGetController")
@RequiredArgsConstructor

public class InterviewerGetController {
	
	private final InterviewerGetService service;
	
	/*
	 * 実施者情報取得処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param id 選択された実施者id
	 * @return 取得結果表示へ
	 * 
	 */
	
	@GetMapping("/manager/interviewer_get")
	public String getInterviewer(HttpSession session, RedirectAttributes redirectAttributes, @RequestParam(required=false) int id) {
		
		Integer loginId = (Integer)session.getAttribute("log_manager_id");
		
		if (loginId == null) {
			
			return "redirect:/manager/login";
			
		}
		
		if (id <= 0) {
			
			return "redirect:/manager/interviewers";
			
		}
		
		String resultMsg = (String)session.getAttribute("signup_result");	
		
		Integer interviewerId = id;
		
		if (resultMsg == null) {
			
			resultMsg = (String)session.getAttribute("set_result");
			
		}
		
		session.removeAttribute("signup_result");
		
		session.removeAttribute("set_result");
		
		Interviewer interviewerInfo = service.getInterviewer(interviewerId);
		
		
		redirectAttributes.addFlashAttribute("interviewer", interviewerInfo);
		
		session.setAttribute("interviewer_get_id", interviewerInfo.getId());
		
		if (resultMsg != null) {
			
			redirectAttributes.addFlashAttribute("result", resultMsg);
		
		}
		
		return "redirect:/manager/interviewer_view";
			
		
	}
	

}
