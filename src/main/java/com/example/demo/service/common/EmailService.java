package com.example.demo.service.common;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 *  メール送信サービスクラス
 * 
 * @author ys
 * 
 */

@Service
@RequiredArgsConstructor
public class EmailService {
	
	private final JavaMailSender mailSender;
	
	/**
	 *  メール送信処理
	 * 
	 * @author ys
	 * 
	 * @param to 宛先メールアドレス
	 * @param subject 件名
	 * @param text メール本文
	 * 
	 */
	
	public void sendEmail(String to, String subject, String text) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(to);
		
		message.setSubject(subject);
		
		message.setText(text);
	
		mailSender.send(message);
		
	
	}

}
