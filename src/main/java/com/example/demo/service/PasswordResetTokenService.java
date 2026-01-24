package com.example.demo.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class PasswordResetTokenService {
	
	private final Map<String, String> tokenMap = new ConcurrentHashMap<>();

	public void put(String email, String token) {
		
		tokenMap.put(email,  token);
		
	}
	
	public Boolean validate(String email, String token) {
		
		return token.equals(tokenMap.get(email));
		
	}
	
	public void remove(String email) {
		
		tokenMap.remove(email);
		
	}
	
	
}
