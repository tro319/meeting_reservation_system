package com.example.demo.controller.manager;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.Manager;
import com.example.demo.service.manager.ManagerGetService;

import lombok.RequiredArgsConstructor;


/*
 * 管理者情報取得処理群
 * 
 * @author ys
 * 
 */

@Controller
@RequiredArgsConstructor

public class ManagerGetController {
	
	private final ManagerGetService service;
	
	/*
	 * 管理者情報取得処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @return 取得結果表示へ
	 * 
	 */
	
	@GetMapping("/manager/manager_get")
	public String getManager(HttpSession session, RedirectAttributes redirectAttributes) {
		
		Integer loginId = (Integer)session.getAttribute("log_manager_id");
		
		if (loginId == null) {
			
			return "redirect:/manager/login";
			
		}
		
		Manager managerInfo = service.getManager(loginId);
		
		
		redirectAttributes.addFlashAttribute("manager", managerInfo);
		
		return "redirect:/manager/manager_view";
			
		
	}
	

}
