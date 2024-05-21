package com.example.demo.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// 로그인이 성공한 사용자 정보 가져오기
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		
		System.out.println("username = " + username);

		// 로그인 성공 후의 동작 구현
		// 예를 들어, 로그인 성공한 사용자에게 특정 페이지로 리다이렉트하거나 로그인 이력을 기록할 수 있습니다.

		// 여기에 로그인 성공 후의 동작을 추가합니다.

		// 예시: 로그인 성공한 사용자를 홈 페이지로 리다이렉트
		response.sendRedirect("/success");
	}
}