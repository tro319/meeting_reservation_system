package com.example.demo.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * メニュー選択ページを表示させる
 * 
 * @author ys
 * 
 * 
 */

@Controller
public class MenuViewController {

	@GetMapping("/users/menu")
	public String view() {
		
		return "users/menu";
		
	}
	
}
