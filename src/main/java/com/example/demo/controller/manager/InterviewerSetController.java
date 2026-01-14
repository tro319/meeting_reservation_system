package com.example.demo.controller.manager;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.Interviewer;
import com.example.demo.model.form.manager.InterviewerSetForm;
import com.example.demo.service.manager.InterviewerSetService;

import lombok.RequiredArgsConstructor;

/*
 * 実施者更新処理群
 * 
 * @author ys
 * 
 */

@Controller("managerInterviewerSetController")
@RequiredArgsConstructor

public class InterviewerSetController {
	
	private final InterviewerSetService service;
	
//	private final PasswordEncoder passEncoder;
	
	/*
	 * 実施者情報取得処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param id 選択された実施者id
	 * @return 取得結果を含む更新フォーム表示へ
	 * 
	 */
	
	@GetMapping("/manager/interviewer_update")
	public String getUser(HttpSession session, RedirectAttributes redirectAttributes, @RequestParam(required=false) int id) {
		
		Integer loginId = (Integer)session.getAttribute("log_manager_id");
		
		if (loginId == null) {
			
			return "redirect:/manager/login";
			
		}
		
		if (id <= 0) {
			
			return "redirect:/manager/interviewers";
			
		}
		
		Integer interviewerId = id;
		
		InterviewerSetForm setForm = (InterviewerSetForm)session.getAttribute("interviewer_set_data");
		
		String setResult = (String)session.getAttribute("set_result");
		
		Interviewer interviewerInfo = null;
		
		if (setForm == null && setResult == null) {
			
			interviewerInfo = service.getInterviewer(interviewerId);
			
			setForm = new InterviewerSetForm();
			
			setForm.setName(interviewerInfo.getName());
			
			setForm.setKana(interviewerInfo.getKana());
			
			setForm.setEmail(interviewerInfo.getEmail());
			
			setForm.setPass(interviewerInfo.getPass());
			
			setForm.setHidName(interviewerInfo.getName());
			
			setForm.setHidKana(interviewerInfo.getKana());
			
			setForm.setHidEmail(interviewerInfo.getEmail());
			
			
			
		}
		
		if (setResult != null) {
			
			redirectAttributes.addFlashAttribute("set_result", setResult);
			
		}
		
		redirectAttributes.addFlashAttribute("interviewer_set_data", setForm);
		
		if (interviewerInfo != null) {
			
			redirectAttributes.addFlashAttribute("interviewer", interviewerInfo);
			
		}
		
		session.setAttribute("interviewer_set_id",  interviewerId);
		
		return "redirect:/manager/interviewer_update_view";
		
		
	}
	
	
	/*
	 * 実施者情報更新処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param form 実施者更新用フォーム入力情報
	 * @return 更新後取得結果表示へ
	 * 
	 */

	@PostMapping("/manager/interviewer_update")
	public String setInterviewer(HttpSession session, RedirectAttributes redirectAttributes, InterviewerSetForm form) {
		
		Integer loginId = (Integer)session.getAttribute("log_manager_id");
		
		if (loginId == null) {
			
			return "redirect:/manager/login";
			
		}
		
		session.setAttribute("interviewer_set_data", form);
		
		Integer interviewerId = (Integer)session.getAttribute("interviewer_set_id");
		
		
		if (!form.getPass().equals(form.getRepass())) {
			
			session.setAttribute("set_result", "パスワード入力が一致しません");
			
			return "redirect:/manager/interviewer_update?id=" + interviewerId;
			
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
			
			session.setAttribute("set_result", "実施者情報が更新されていません");
		
			return "redirect:/manager/interviewer_update?id=" + interviewerId;
			
		}
		
		updates.put("pass", form.getPass());
		
		Interviewer setInterviewer = service.setInterviewer(interviewerId, updates, loginId);
		
		
		session.setAttribute("set_result", "実施者情報更新しました");
		
		session.removeAttribute("interviewer_set_data");
		
		return "redirect:/manager/interviewer_get?id=" + interviewerId;
		
		
	}
	
	
	/*
	 * 実施者情報更新フォーム取得へ戻る処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @return 実施者情報取得処理へ
	 * 
	 */

	@GetMapping("/manager/interviewer_update/return")
	public String back(HttpSession session, RedirectAttributes redirectAttributes) {
		
		Integer loginId = (Integer)session.getAttribute("log_manager_id");
		
		if (loginId == null) {
			
			return "redirect:/manager/login";
			
		}
		
		Integer interviewerId = (Integer)session.getAttribute("interviewer_set_id");
		
		return "redirect:/manager/interviewer_update?id=" + interviewerId;
		
		
		
	}

}

