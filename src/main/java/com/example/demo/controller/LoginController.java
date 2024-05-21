package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login() {

		return "login";
	}

	@GetMapping("/register")
	public String register(Model model) {

		User user = new User();
		model.addAttribute("user", user);

		return "register";
	}

	@PostMapping("/register")
	public String register(User user) {

		userService.save(user);
		return "redirect:/login";
	}

	@GetMapping("/logout")
	public String logout() {
		// 로그아웃 후에는 홈 페이지로 리다이렉트합니다.
		return "redirect:/login";
	}
	
	@GetMapping("/success")
	public String successPage() {

	    return "success";
	}
}