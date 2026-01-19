package com.example.demo.controller.user;

import java.time.format.DateTimeFormatter;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.Interviewer;
import com.example.demo.model.entity.Reservation;
import com.example.demo.model.entity.Time;
import com.example.demo.model.entity.User;
import com.example.demo.model.form.user.ReservationRegisterForm;
import com.example.demo.repository.InterviewersRepository;
import com.example.demo.repository.TimesRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.MailSendService;
import com.example.demo.service.user.ReservationRegisterService;

import lombok.RequiredArgsConstructor;


/*
 * 予約登録確認処理群
 * 
 * @author ys
 * 
 */

@Controller
@RequiredArgsConstructor

public class ReservationRegisterController {
	
	private final ReservationRegisterService service;
	
	private final MailSendService mailService;
	
	private final TimesRepository timesRepository;
	
	private final UsersRepository usersRepository;
	
	private final InterviewersRepository interviewersRepository;
	
	
	/*　予約登録確認処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param date 選択された日付
	 * @param time 選択された時間id
	 * @param interviewerId 選択された実施者id
	 * @return 予約登録確認表示のパスへ
	 * 
	 */
	
	@GetMapping("/user/reservation_register_confirm")
	
	public String confirm(HttpSession session, RedirectAttributes redirectAttributes, @RequestParam String date, @RequestParam(name="time") Integer timeId, @RequestParam(name="interviewer_id") Integer interviewerId) {
		
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		// ログインしているかどうか
		
		if (loginId == null) {
			
			return "redirect:/user/login";
			
		}
		
		ReservationRegisterForm registerForm = new ReservationRegisterForm();
		
		registerForm.setDate(date);
		
		registerForm.setTimeId(timeId);
		
		registerForm.setUserId(loginId);
		
		registerForm.setInterviewerId(interviewerId);
		
		session.setAttribute("reservation_register_form", registerForm);
		
		
		Time timeInfo = timesRepository.findById(registerForm.getTimeId()).orElse(null);
		
		User userInfo = usersRepository.findById(registerForm.getUserId()).orElse(null);
		
		Interviewer interviewerInfo = interviewersRepository.findById(registerForm.getInterviewerId()).orElse(null);
		
		session.setAttribute("reservation_date", registerForm.getDate());
		
		session.setAttribute("reservation_time", timeInfo);
		
		session.setAttribute("reservation_user", userInfo);
		
		session.setAttribute("reservation_interviewer", interviewerInfo);
		
		session.removeAttribute("selected_interviewer_id");
		
		return "redirect:/user/reservation_register_view";
		
		
	}

	/*　予約登録失敗時戻す処理
	 * 
	 * @param session セッション値情報
	 * @return 実施者一覧取得のパスへ
	 * 
	 */
	
	@GetMapping("/user/reservation_register/return")
	
	public String back(HttpSession session) {
		
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		// ログインしているかどうか
		
		if (loginId == null) {
			
			return "redirect:/user/login";
			
		}
		
		
		return "redirect:/user/interviewers";
		
		
	}
	
	/*　予約登録処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param form 予約登録フォーム入力情報
	 * @return 予約情報取得のパスへ
	 * 
	 */
	
	@PostMapping("/user/reservation_register")
	
	public String register(HttpSession session, RedirectAttributes redirectAttributes, ReservationRegisterForm form) {
		
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		// ログインしているかどうか
		
		if (loginId == null) {
			
			return "redirect:/user/login";
			
		}
		
		session.removeAttribute("reservation_register_form");
		
		session.removeAttribute("reservation_date");
		
		session.removeAttribute("reservation_time");
		
		session.removeAttribute("reservation_user");
		
		session.removeAttribute("reservation_interviewer");
		
		Boolean doubleCheck = service.doubleCheck(form);
		
		if (doubleCheck) {
			
			session.setAttribute("register_result", "予約が重複しているため、別の予約枠を選択してください");
			
			return "redirect:/user/reservation_register/return";
			
		}
		
		Reservation reservationInfo = service.registerReservation(form);
		
		String registerName = reservationInfo.getUser().getName();
		
		String registerEmail = reservationInfo.getUser().getEmail();
		
		String registerSendText = registerName + "さん、" + "下記日程で面談予約が完了しました。\n\n" + "面談日: " + reservationInfo.getDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + "\n開始時間: " + reservationInfo.getTime().getStart() + "時\n担当者名: " + reservationInfo.getInterviewer().getName() + "\nユーザー名: " + reservationInfo.getUser().getName();
		
		mailService.sendMail(registerEmail, "面談予約が完了しました! | 面談予約システム", registerSendText);
		
		session.setAttribute("register_result", "予約が完了しました。");
		
		
		return "redirect:/user/reservation?id=" + reservationInfo.getId();
		
		
	}
	
}
