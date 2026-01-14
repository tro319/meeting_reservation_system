package com.example.demo.controller.manager;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.Interviewer;
import com.example.demo.model.form.interviewer.SearchForm;
import com.example.demo.service.manager.InterviewersGetService;

import lombok.RequiredArgsConstructor;


/*
 * 実施者一覧取得処理群 (検索後)
 * 
 * @author ys
 * 
 */

@Controller("managerSearchInterviewersGetController")
@RequiredArgsConstructor

public class SearchInterviewersGetController {
	
	private final InterviewersGetService service;
	
	
	/*　実施者一覧取得処理 (検索後)
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param form 検索フォーム入力情報
	 * @return 実施者一覧表示のパスへ
	 * 
	 */
	
	@GetMapping("/manager/interviewers/search")
	
	public String getReservations(HttpSession session, RedirectAttributes redirectAttributes, SearchForm form) {
		
		Integer loginId = (Integer)session.getAttribute("log_manager_id");
		
		// ログインしているかどうか
		
		if (loginId == null) {
			
			return "redirect:/manager/login";
			
		}
		
		// 検索フォームが空なのか
		
		if (form.getName() == null || form.getName().isEmpty()) {
			
			return "redirect:/manager/interviewers";
			
		}
		
		
		
		
		List<Interviewer> interviewerInfos = service.getInterviewers();
		
		
		// 各検索値で絞り込み
		
		
		if (form.getName() != null && !form.getName().isEmpty()) {
			
			interviewerInfos = interviewerInfos.stream().filter(interviewer -> interviewer.getName().contains(form.getName()) || interviewer.getKana().contains(form.getName()) ).toList();
			
		}
		
		
		
		session.setAttribute("search_form", form);
		
		redirectAttributes.addFlashAttribute("search_form", form);
		
		redirectAttributes.addFlashAttribute("interviewers", interviewerInfos);
		
		
		return "redirect:/manager/interviewers_view/search";
		
		
	}

}
