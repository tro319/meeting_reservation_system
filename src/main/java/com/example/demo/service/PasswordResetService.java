package com.example.demo.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/*
 * パスワードリセットサービス群
 * 
 * @author ys
 * 
 */

@Service
@RequiredArgsConstructor

public class PasswordResetService {
	
	
	private final MailSendService mailService;
	
	private final PasswordResetTokenService tokenService;
	
	
	/* パスワードリセットメール送信処理
	 * 
	 * @param email 入力されたメールアドレス
	 * 
	 */
	
	public void sendResetEmail(String email) {
		
		String token = UUID.randomUUID().toString();
		
		tokenService.put(email,  token);
		
		String sendText = "いつも当サービス「面談予約システム」をご利用頂きありがとうございます。\n以下のURLからパスワードをリセットしてください。\n" + "http://localhost:8080/user/pass_reset?email=" + email + "&token=" + token;
		
		String subject = "パスワードリセットのお知らせ | 面談予約システム";
		
		mailService.sendMail(email, subject,  sendText);
		
	}
	

	/* パスワードリセットトークン照合処理
	 * 
	 * @param email 入力されたメールアドレス
	 * @param token 入力されたトークン
	 * @return 照合したかどうかのT/F
	 */
	
	public Boolean validateToken(String email, String token) {
		
		return tokenService.validate(email, token);
		
	}
	
	
	/* パスワードリセットトークン削除処理
	 * 
	 * @param email 入力されたメールアドレス
	 * 
	 */
	
	public void removeToken(String email) {
		
		tokenService.remove(email);
		
	}
	

}
