package com.example.demo.controller.user;

import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.Time;
import com.example.demo.service.user.ReservationAblesGetService;
import com.example.demo.service.user.TimesGetService;

import lombok.RequiredArgsConstructor;


/*
 * 予約空き状況一覧取得処理群
 * 
 * @author ys
 * 
 */

@Controller("userReservationAblesGetController")
@RequiredArgsConstructor

public class ReservationAblesGetController {
	
	private final ReservationAblesGetService service;
	
	private final TimesGetService timesService;
	
	/*　予約空き状況一覧取得処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param interviewerId 予約分を担当する、実施者id
	 * @return 予約空き状況一覧表示のパスへ
	 * 
	 */
	
	@GetMapping("/user/reservation_ables_get")
	
	public String getReservationAbles(HttpSession session, RedirectAttributes redirectAttributes, @RequestParam(name="interviewer_id", required=false) int interviewerId) {
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		// ログインしているかどうか
		
		if (loginId == null) {
			
			return "redirect:/user/login";
			
		}
		
		if (interviewerId <= 0) {
			
			return "redirect:/user/interviewers";
			
		}
		
		
		Integer afterInterviewerId = interviewerId;
		
		session.setAttribute("selected_interviewer_id", afterInterviewerId);
		
		Map<String, Map<Integer, Boolean>> reservationAbleInfos = service.getReservationAbles(afterInterviewerId);
		
		List<Time> timeInfos = timesService.getTimes();
		
		

		
		
		redirectAttributes.addFlashAttribute("reservation_ables", reservationAbleInfos);
		
		redirectAttributes.addFlashAttribute("dates", reservationAbleInfos.keySet());
		
		redirectAttributes.addFlashAttribute("times", timeInfos);
		
		redirectAttributes.addFlashAttribute("interviewer_id", afterInterviewerId);
		
		return "redirect:/user/reservation_ables_view";
		
		
	}

}
