package com.example.demo.comfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.handler.CustomAuthenticationFailureHandler;
import com.example.demo.handler.CustomAuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
			// 사이트 위변조 요청 방지
			.csrf(AbstractHttpConfigurer::disable)

			// 인가(접근권한) 설정
			.authorizeHttpRequests(authorize  -> authorize 
					.requestMatchers("/login").permitAll()
					.requestMatchers("/register").permitAll()
	                .anyRequest().authenticated()
			)
			
			// 로그인 설정
			.formLogin(login -> login
								.loginPage("/login")
								.successHandler(customAuthenticationSuccessHandler)
								.failureHandler(customAuthenticationFailureHandler)
								
			)
			
			// 로그아웃 설정
			.logout(logout -> logout
								.invalidateHttpSession(true)
								.logoutUrl("/logout")
			);
		

		return http.build();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

