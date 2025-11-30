package com.example.demo.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/* 
 * メール送信処理群
 * 
 * @author ys
 * 
 */

@Service
@RequiredArgsConstructor

public class MailSendService {

	private final JavaMailSender mailSender;
	
	/*
	 * メール送信処理
	 * 
	 * @param to 宛先
	 * @param subject 件名
	 * @param text 本文
	 * 
	 */
	
	public void sendMail(String to, String subject, String text) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(to);
		
		message.setSubject(subject);
		
		message.setText(text);
		
		mailSender.send(message);
		
	}
	
}
