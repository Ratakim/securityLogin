package com.example.demo.handler;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// 로그인이 실패한 사용자의 정보 가져오기
		String username = request.getParameter("username");
		
		System.out.println("username = " + username);

		// 로그인 실패 시의 동작 구현
		// 예를 들어, 로그인 실패 이유에 따라 다른 메시지를 사용자에게 표시하거나 로그인 이력을 기록할 수 있습니다.

		// 여기에 로그인 실패 시의 동작을 추가합니다.

		// 예시: 로그인 실패 이유를 사용자에게 알려주고 로그인 페이지로 다시 리다이렉트
		request.getSession().setAttribute("loginErrorMessage", "로그인 실패했습니다. 다시 시도해주세요.");
		response.sendRedirect("/login");
	}
}